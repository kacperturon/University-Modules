using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Data.Entity.Migrations;
using System.Data.Entity.Validation;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.Mvc;
using COMP1632.Models;
using COMP1632.ViewModels;
using WebGrease.Css.Extensions;

namespace COMP1632.Controllers
{
    [Authorize(Roles = "SalesStaff")]
    public class CustomersController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        // GET: Customers
        public ActionResult Index()
        {
            var customers = db.Customers.Include(c => c.CreditCard).Include(c => c.ShoppingCart).Include(c => c.User);
            return View(customers.ToList());
        }

        // GET: Customers/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Customer customer = db.Customers.Find(id);
            if (customer == null)
            {
                return HttpNotFound();
            }
            return View(customer);
        }

        // GET: Customers/Create
        public ActionResult Create()
        {
            RegisterAuthenticationViewModel viewModel = new RegisterAuthenticationViewModel();
            return View(viewModel);
        }

        // POST: Customers/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(RegisterAuthenticationViewModel viewModel)
        {
            if (ModelState.IsValid)
            {
                if (!db.Users.Any(u => u.Username.Equals(viewModel.Username)))
                {
                    Customer customer = new Customer()
                    {
                        User = new User()
                        {
                            FirstName = viewModel.FirstName,
                            Surname = viewModel.Surname,
                            Username = viewModel.Username,
                            Password = viewModel.Password,
                            UserRole = db.UserRoles.FirstOrDefault(u => u.Role.Equals("Customer"))
                        },
                        CreditCard = new CreditCard()
                        {
                            NameOnCard = viewModel.NameOnCard,
                            CardNumber = viewModel.CardNumber,
                            CCV = viewModel.CCV,
                            ExpiryDate = viewModel.ExpiryDate
                        },
                        Email = viewModel.Email,
                        ShoppingCart = new ShoppingCart()

                    };
                    db.Customers.Add(customer);
                    db.SaveChanges();
                    TempData["MessageClass"] = "alert-success";
                    TempData["Message"] = "Successful registration.";

                    return RedirectToAction("Index");
                }
                ModelState.AddModelError("", viewModel.Username + " already exists in the system.");

            }
            return RedirectToAction("Index");
        }

        // GET: Customers/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Customer customer = db.Customers.Find(id);
            if (customer == null)
            {
                return HttpNotFound();
            }
            RegisterAuthenticationViewModel viewModel = new RegisterAuthenticationViewModel()
            {
                CustomerId = customer.User.Id,
                Username = customer.User.Username,
                Password = customer.User.Password,
                FirstName = customer.User.FirstName,
                Surname = customer.User.Surname,
                Email = customer.Email,
                NameOnCard = customer.CreditCard.NameOnCard,
                CardNumber = customer.CreditCard.CardNumber,
                ExpiryDate = customer.CreditCard.ExpiryDate,
                CCV = customer.CreditCard.CCV

            };

            return View(viewModel);
        }

        // POST: Customers/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(RegisterAuthenticationViewModel viewModel)
        {
            if (ModelState.IsValid)
            {
                Customer customerOriginal = db.Customers.Find(viewModel.CustomerId);
                customerOriginal.User.FirstName = viewModel.FirstName;
                customerOriginal.User.Surname = viewModel.Surname;
                customerOriginal.User.Username = viewModel.Username;
                customerOriginal.User.Password = viewModel.Password;
                customerOriginal.Email = viewModel.Email;
                customerOriginal.CreditCard.NameOnCard = viewModel.NameOnCard;
                customerOriginal.CreditCard.CardNumber = viewModel.CardNumber;
                customerOriginal.CreditCard.CCV = viewModel.CCV;
                customerOriginal.CreditCard.ExpiryDate = viewModel.ExpiryDate;
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return RedirectToAction("Index");
        }

        // GET: Customers/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Customer customer = db.Customers.Find(id);
            if (customer == null)
            {
                return HttpNotFound();
            }

            db.Enquiries.RemoveRange(db.Enquiries.Where(x => x.Customer.User.Id == customer.User.Id));
            db.CreditCards.RemoveRange(db.CreditCards.Where(x => x.Customer.User.Id == customer.User.Id));
            db.ShoppingCarts.RemoveRange(db.ShoppingCarts.Where(x => x.Customer.User.Id == customer.User.Id));
            db.Tickets.RemoveRange(db.Tickets.Where(x => x.Customer.User.Id == customer.User.Id));
            db.Reviews.RemoveRange(db.Reviews.Where(x => x.Customer.User.Id == customer.User.Id));
            db.OrderItems.Where(x => x.Order.Customer.User.Id == customer.User.Id).ForEach(x=>db.OrderItems.Remove(x));
            db.Orders.RemoveRange(db.Orders.Where(x => x.Customer.User.Id == customer.User.Id));
            db.Users.RemoveRange(db.Users.Where(x => x.Id == customer.User.Id));

            db.Customers.Remove(customer);
            db.SaveChanges();
            
            return RedirectToAction("Index");
        }

    }
}
