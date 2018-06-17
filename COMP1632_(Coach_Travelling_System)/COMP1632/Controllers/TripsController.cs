using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using COMP1632.Models;
using COMP1632.ViewModels;

namespace COMP1632.Controllers
{
    [Authorize(Roles = "SalesStaff")]
    public class TripsController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        // GET: Trips
        public ActionResult Index()
        {
            var trips = db.Trips.Include(t => t.Coach);
            return View(trips.ToList());
        }

        // GET: Trips/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Trip trip = db.Trips.Find(id);
            if (trip == null)
            {
                return HttpNotFound();
            }
            return View(trip);
        }

        // GET: Trips/Create
        public ActionResult Create()
        {
            CreateTripViewModel viewModel = new CreateTripViewModel();
            
            return View(viewModel);
        }

        // POST: Trips/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(CreateTripViewModel viewModel)
        {
            if (ModelState.IsValid)
            {
                Trip trip = new Trip();

                trip.Title = viewModel.Title;
                trip.Description = viewModel.Description;
                trip.From = viewModel.From;
                trip.To = viewModel.To;
                trip.DepartureDate = viewModel.DepartureDate;
                trip.ReturnDate = viewModel.DepartureDate;
                trip.Price = viewModel.Price;

                trip.Coach = new Coach();
                trip.Coach.Capacity = viewModel.Capacity;

                db.Trips.Add(trip);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return RedirectToAction("Index");
        }

        // GET: Trips/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Trip trip = db.Trips.Find(id);
            if (trip == null)
            {
                return HttpNotFound();
            }
            ViewBag.Id = new SelectList(db.Coaches, "TripId", "TripId", trip.Id);
            return View(trip);
        }

        // POST: Trips/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(Trip trip)
        {
            if (ModelState.IsValid)
            {
                db.Entry(trip).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.Id = new SelectList(db.Coaches, "TripId", "TripId", trip.Id);
            return View(trip);
        }

        // GET: Trips/Delete/5
        public ActionResult Delete(int? id)
        {
            Trip trip = db.Trips.Find(id);
            db.TripImages.RemoveRange(db.TripImages.Where(x => x.Trip.Id == trip.Id));
            db.Reviews.RemoveRange(db.Reviews.Where(x => x.Trip.Id == trip.Id));
            db.Tickets.RemoveRange(db.Tickets.Where(x => x.Trip.Id == trip.Id));
            db.CartItems.RemoveRange(db.CartItems.Where(x => x.Trip.Id == trip.Id));
            db.Coaches.RemoveRange(db.Coaches.Where(x => x.Trip.Id == trip.Id));
            db.Trips.Remove(trip);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

    }
}
