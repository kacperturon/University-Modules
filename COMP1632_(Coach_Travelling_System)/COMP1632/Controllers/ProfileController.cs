using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web.Mvc;
using COMP1632.Models;
using COMP1632.ViewModels;

namespace COMP1632.Controllers
{
    [Authorize(Roles="Customer")]
    public class ProfileController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        // GET: Profile
        public ActionResult Index()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase)); //Get currently logged in user
            Customer customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)); //Get customer account associated with that user
            return View(customer);
        }

        // GET: Profile/Trips
        public ActionResult Trips()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            Customer customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id));

            List<Trip> Trips = db.Tickets.Where(p => p.Customer.UserId == customer.UserId).Select(q => q.Trip).Distinct().ToList(); //Get trips this customer has tickets to
            Trips.ForEach(t => t.Tickets.RemoveAll(u => u.Customer.UserId != customer.UserId)); //get customer's tickets

            TripsProfileViewModel viewModel = new TripsProfileViewModel()
            {
                Trips = Trips,
            };

            return View(viewModel);
        }

        // GET: Profile/Enquiries
        public ActionResult Enquiries()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            Customer customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id));

            var enquiries = db.Enquiries.Where(u => u.Customer.User.Id.Equals(customer.User.Id)); //Get customers enquiries

            return View(enquiries);
        }

        // GET: Profile/Orders
        public ActionResult Orders()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            Customer customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id));
            
            return View(customer.Orders); //return customer orders
        }
      
        // GET: Profile/Review
        public ActionResult Review(int TripId, int OrderItemId)
        {
            Trip trip = db.Trips.Find(TripId); //get trip by id passed 
            return View(new ReviewProfileViewModel() {TripId = trip.Id, TripTitle = trip.Title, OrderItemId = OrderItemId});
        }

        [HttpPost]
        public ActionResult Review(ReviewProfileViewModel viewModel)
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            Customer customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id));

            Trip trip = db.Trips.Find(viewModel.TripId); //get trip customer tries to write review for

            Review review = new Review() //create the review object from retrived model
            {
                Customer = customer,
                Trip = trip,
                Description = viewModel.Description,
                Rating = viewModel.Rating,
            };
            OrderItem orderItem = db.OrderItems.Find(viewModel.OrderItemId);
            orderItem.ReviewId = review.Id;
            try //add review to database and pass success message
            {
                db.Reviews.Add(review);
                db.OrderItems.AddOrUpdate(orderItem);
                db.SaveChanges();
                TempData["MessageClass"] = "alert-success";
                TempData["Message"] = "Successfully added the review.";
            }
            catch (Exception ex) //In case of errors pass unsuccessfull message
            {
                TempData["MessageClass"] = "alert-danger";
                TempData["Message"] = "Unsuccessful - please try again later.";
            }
            return RedirectToAction("Orders"); //Redirect back to orders page 
        }

        public ActionResult AmendBooking(int id)
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            Customer customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id));
            Trip trip = db.Trips.Find(id);
            List<Ticket> tickets = trip.Tickets.Where(t => t.Customer.Equals(customer)).ToList();
            double hours = (trip.DepartureDate - DateTime.Now).TotalHours;

            if (hours > 24)
            {
                try 
                {
                    db.Tickets.RemoveRange(tickets);
                    db.SaveChanges();
                    TempData["MessageClass"] = "alert-success";
                    TempData["Message"] = "Successfully amended booking.";
                }
                catch (Exception ex) //In case of errors pass unsuccessfull message
                {
                    TempData["MessageClass"] = "alert-danger";
                    TempData["Message"] = "Unsuccessful - please try again later.";
                }
            }
            else
            {
                TempData["MessageClass"] = "alert-danger";
                TempData["Message"] = "Unable to amend a booking 24 hours before departure.";
            }
            return RedirectToAction("Trips");
        }

    }
}