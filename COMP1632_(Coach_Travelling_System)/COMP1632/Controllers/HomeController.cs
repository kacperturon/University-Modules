using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using COMP1632.Models;
using COMP1632.ViewModels;
using WebGrease.Css.Extensions;

namespace COMP1632.Controllers
{
    public class HomeController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        // GET: Home
        public ActionResult Index()
        {
            var tripsInfo = new List<IndexHomeViewModel.TripInfo>();
            db.Trips.ForEach(t=> tripsInfo.Add(new IndexHomeViewModel.TripInfo()
            {
                Trip = t,
                TicketsLeft = t.Coach.Capacity - t.Tickets.Count
            }));

            IndexHomeViewModel viewModel = new IndexHomeViewModel()
            {
                TripsInfo = tripsInfo
            };

            return View(viewModel);
        }

        [HttpPost]
        public ActionResult Index(IndexHomeViewModel viewModel)
        {
            var trips = db.Trips.ToList();

            if (!string.IsNullOrEmpty(viewModel.From))
            {
                trips = trips.Where(u => u.From.ToLower().Equals(viewModel.From.ToLower())).ToList();
            }
            if (!string.IsNullOrEmpty(viewModel.To))
            {
                trips = trips.Where(u => u.To.ToLower().Equals(viewModel.To.ToLower())).ToList();
            }
            if (viewModel.PriceMax>0&&viewModel.PriceMin<viewModel.PriceMax)
            {
                trips = trips.Where(u => u.Price >= viewModel.PriceMin && u.Price <= viewModel.PriceMax).ToList();
            }
            viewModel = new IndexHomeViewModel();
            var tripsInfo = new List<IndexHomeViewModel.TripInfo>();
            trips.ForEach(t => tripsInfo.Add(new IndexHomeViewModel.TripInfo()
            {
                Trip = t,
                TicketsLeft = t.Coach.Capacity - t.Tickets.Count
            }));
            viewModel.TripsInfo = tripsInfo;

            return View(viewModel);
        }

        public ActionResult Details(int id)
        {
            Trip trip = db.Trips.Find(id);

            string path = null;
            if (trip.TripImages.Count>0)
            {
                path = trip.Id + "_" + trip.TripImages[0].Id + ".jpg";
            }

            DetailsHomeViewModel detailsHomeViewModel = new DetailsHomeViewModel()
            {
                TripId = trip.Id,
                Title = trip.Title,
                Description = trip.Description,
                DepartureDate = trip.DepartureDate,
                ReturnDate = trip.ReturnDate,
                From = trip.From,
                To = trip.To,
                Price = trip.Price,
                MainImageFilename = path,
                TripImages = trip.TripImages,
                Tickets = trip.Tickets,
                Reviews = trip.Reviews
            };

            return View(detailsHomeViewModel);
        }

        [ChildActionOnly]
        public ActionResult ReviewAverage(int? id)
        {
            double average;
            Trip trip = db.Trips.Find(id);
            List<int> values = trip.Reviews.Select(review => review.Rating).ToList();
            if (values.Count > 0)
            {
                average = Math.Round(values.Average(), 2);
            }
            else
            {
                average = 0.0;
            }

            ViewData["ReviewAverage"] = average;
            return PartialView("_ReviewAverage", id);
        }

        public ActionResult Reviews(int? id)
        {
            Trip trip = db.Trips.Find(id);

            return View(trip);
        }

    }
}