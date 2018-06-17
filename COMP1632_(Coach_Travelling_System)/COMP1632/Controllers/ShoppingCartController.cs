using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using COMP1632.Models;
using COMP1632.ViewModels;

namespace COMP1632.Controllers
{
    [Authorize(Roles = "Customer")]
    public class ShoppingCartController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        // GET: ShoppingCart
        public ActionResult Index()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase)); //Get currently logged in user
            ShoppingCart shoppingCart = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)).ShoppingCart; //Get customer associated with logged in customer

            IndexShoppingCartViewModel shoppingCartViewModel = new IndexShoppingCartViewModel()
            {
                ShoppingCart = shoppingCart,
                Total = shoppingCart.GetTotal()
            };
       
            return View(shoppingCartViewModel);
        }

        // POST: ShoppingCart/AddToCart/
        [HttpPost]
        public ActionResult AddToCart(int tripId, int quantity)
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            ShoppingCart shoppingCart = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)).ShoppingCart;

            shoppingCart.AddToCart(tripId, quantity);
            TempData["Message"] = "Trip added to basket.";

            return Redirect(Request.UrlReferrer.ToString());

        }

        public ActionResult ClearCart()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            ShoppingCart shoppingCart = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)).ShoppingCart;

            shoppingCart.ClearCart();

            return RedirectToAction("Index");
        }

        [HttpPost]
        public ActionResult Preferences(IndexShoppingCartViewModel viewModel)
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            ShoppingCart shoppingCart = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)).ShoppingCart;

            if (!shoppingCart.AreTicketsAvailable())
            {
                TempData["Message"] = "There is not enough of tickets for selected trips.";
                return RedirectToAction("Index");
            }

            var cartItemPreferences = new List<PreferencesShoppingCartViewModel.CartItemPreference>();
            shoppingCart.CartItems.ForEach(cartI=>cartItemPreferences.Add(new PreferencesShoppingCartViewModel.CartItemPreference()
            {
                CartItemId = cartI.Id,
                Title = cartI.Trip.Title
            }));
            var viewModelNew = new PreferencesShoppingCartViewModel()
            {
                CartItemPreferences = cartItemPreferences
            };
            viewModelNew.PreferenceOptions = new List<SelectListItem>()
                {
                    new SelectListItem()
                    {
                        Value = "0",
                        Text = "Random"
                    },
                    new SelectListItem()
                    {
                        Value = "1",
                        Text = "Adjacent"
                    },
                    new SelectListItem()
                    {
                        Value = "2",
                        Text = "In the middle"
                    },
                    new SelectListItem()
                    {
                        Value = "3",
                        Text = "Window seat"
                    }

                };
            return View(viewModelNew);
        }

        [HttpPost]
        public ActionResult Checkout(PreferencesShoppingCartViewModel viewModel)
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            ShoppingCart shoppingCart = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)).ShoppingCart;

            foreach (var preference in viewModel.CartItemPreferences)
            {
                CartItem cI = shoppingCart.CartItems.Find(x => x.Id.Equals(preference.CartItemId));
                cI.SeatPreference = int.Parse(preference.Selected);
                db.CartItems.AddOrUpdate(cI);
                db.SaveChanges();
            }

            Order order = shoppingCart.CreateOrder();

            if (order != null)
            {
                TempData["Message"] = "Successfully ordered.";
            }
            else
            {
                TempData["Message"] = "Unsuccessful order.";
                return RedirectToAction("Index");
            }

            return View(order);
        }

        [ChildActionOnly]
        public ActionResult CartSummary()
        {
            User user = db.Users.FirstOrDefault(u => u.Username.Equals(User.Identity.Name, StringComparison.CurrentCultureIgnoreCase));
            ShoppingCart shoppingCart = db.Customers.FirstOrDefault(u => u.UserId.Equals(user.Id)).ShoppingCart;

            ViewData["CartCount"] = shoppingCart.CartItems.Count;
            return PartialView("_CartSummary");
        }
    }
}