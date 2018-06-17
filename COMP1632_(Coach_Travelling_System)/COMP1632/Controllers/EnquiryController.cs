using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using COMP1632.Models;
using COMP1632.ViewModels;

namespace COMP1632.Controllers
{
    public class EnquiryController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        [Authorize(Roles = "Customer")]
        public ActionResult New(int? id)
        {
            var trip = db.Trips.Find(id);

            NewEnquiryViewModel newEnquiryViewModel = new NewEnquiryViewModel()
            {
                TitleOptions = new List<SelectListItem>()
                {
                    new SelectListItem()
                    {
                        Value = "0",
                        Text = "General"
                    },
                    new SelectListItem()
                    {
                        Value = trip.Id.ToString(),
                        Text = trip.Title
                    }
                },
                TripId = trip.Id
            };

            return View(newEnquiryViewModel);
        }

        [Authorize(Roles = "Customer")]
        [HttpPost]
        public ActionResult New(NewEnquiryViewModel viewModel)
        {
            if (ModelState.IsValid)
            {
                User user =
                    db.Users.FirstOrDefault(
                        u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
                var Customer = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id));
                string title = "General";
                if (viewModel.SelectedTitleOption != "0")
                {
                    var tripId = int.Parse(viewModel.SelectedTitleOption);
                    title =
                        db.Trips.FirstOrDefault(u => u.Id.Equals(tripId)).Title;
                }
                Enquiry enquiry = new Enquiry()
                {
                    Title = title,
                    Question = viewModel.Question,
                    Customer = Customer
                };
                try
                {
                    db.Enquiries.Add(enquiry);
                    db.SaveChanges();
                    TempData["MessageClass"] = "alert-success";
                    TempData["Message"] = "Successfully sent the enquiry.";
                }
                catch (Exception ex)
                {
                    TempData["MessageClass"] = "alert-warning";
                    TempData["Message"] = "Something went wrong. Try again later.";
                }
                System.Diagnostics.Debug.WriteLine(viewModel.TripId);
                return RedirectToAction("Details", "Home", new {id = viewModel.TripId});
            }
            viewModel.TitleOptions
                = new List<SelectListItem>()
                {
                    new SelectListItem()
                    {
                        Value = "0",
                        Text = "General"
                    },
                    new SelectListItem()
                    {
                        Value = viewModel.TripId.ToString(),
                        Text = db.Trips.Find(viewModel.TripId).Title
                    }
                };
              

            return View(viewModel);
        }

        [Authorize(Roles = "SalesStaff")]
        public ActionResult Unanswered()
        {
            List<Enquiry> enquiries = new List<Enquiry>();
            enquiries = db.Enquiries.Where(u => string.IsNullOrEmpty(u.Answer)).ToList();
            return View(enquiries);
        }

        [Authorize(Roles = "SalesStaff")]
        public ActionResult Answered()
        {
            List<Enquiry> enquiries = new List<Enquiry>();
            enquiries = db.Enquiries.Where(u => !string.IsNullOrEmpty(u.Answer)).ToList();
            return View(enquiries);
        }

        [Authorize(Roles = "SalesStaff")]
        public ActionResult All()
        {
            List<Enquiry> enquiries = new List<Enquiry>();
            enquiries = db.Enquiries.ToList();
            return View(enquiries);
        }

        [Authorize(Roles = "SalesStaff")]
        public ActionResult Answer(int id)
        {

            Enquiry enquiry = db.Enquiries.FirstOrDefault(u => u.Id.Equals(id));
            AnswerEnquiryViewModel answerEnquiryViewModel = new AnswerEnquiryViewModel()
            {
                Id = enquiry.Id,
                Answer = enquiry.Answer,
                Question = enquiry.Question,
                Customer = enquiry.Customer,
                SalesStaff = enquiry.SalesStaff,
                Title = enquiry.Title
            };
            return View(answerEnquiryViewModel);
        }

        [Authorize(Roles = "SalesStaff")]
        [HttpPost]
        public ActionResult Answer(AnswerEnquiryViewModel answerEnquiryViewModel)
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            var salesStaff = db.SalesStaffs.FirstOrDefault(u => u.UserId.Equals(user.Id));
            var enquiry = db.Enquiries.FirstOrDefault(u => u.Id.Equals(answerEnquiryViewModel.Id));
            enquiry.Answer = answerEnquiryViewModel.Answer;
            enquiry.SalesStaff = salesStaff;

            try
            {
                db.Enquiries.AddOrUpdate(enquiry);
                db.SaveChanges();
                TempData["MessageClass"] = "alert-success";
                TempData["Message"] = "Successfully responded to enquiry.";
            }
            catch (Exception ex)
            {
                TempData["MessageClass"] = "alert-warning";
                TempData["Message"] = "Something went wrong. Try again later.";
            }
            
            return RedirectToAction("Unanswered");
        }
    }
}