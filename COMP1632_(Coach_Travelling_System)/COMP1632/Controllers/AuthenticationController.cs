using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using COMP1632.Models;
using System.Data.Entity;
using System.Web.Security;
using COMP1632.ViewModels;


namespace COMP1632.Controllers
{
    public class AuthenticationController : Controller
    {
        private SchemaDBContext db = new SchemaDBContext();

        // GET: Login
        public ActionResult Login()
        {
            LoginAuthenticationViewModel viewModel = new LoginAuthenticationViewModel();
            return View(viewModel);
        }

        [HttpPost]
        public ActionResult Login(LoginAuthenticationViewModel viewModel)
        {
            User user = null;
            if (ModelState.IsValid)
            {
                user = db.Users.FirstOrDefault(u => u.Username.Equals(viewModel.Username) && u.Password.Equals(viewModel.Password));
                if (user != null)
                {
                    FormsAuthentication.SetAuthCookie(user.Username, false);
                    return RedirectToAction("Index", "Home");
                }
                ModelState.AddModelError("", "Wrong username and/or password");
            }
            return View(user);
        }

        public ActionResult Register()
        {
            RegisterAuthenticationViewModel RegisterAuthenticationViewModel = new RegisterAuthenticationViewModel();
            return View(RegisterAuthenticationViewModel);
        }
        
        [HttpPost]
        public ActionResult Register(RegisterAuthenticationViewModel viewModel)
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

                    return RedirectToAction("Index", "Home");
                }
                ModelState.AddModelError("", viewModel.Username+" already exists in the system.");

            }
            return View(viewModel);

        }

        [Authorize]
        public ActionResult Logout()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("Index", "Home");
        }
    }
}