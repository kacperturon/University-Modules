using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class PreferencesShoppingCartViewModel
    {
        public IEnumerable<SelectListItem> PreferenceOptions { get; set; }
        public List<CartItemPreference> CartItemPreferences { get; set; }

        public class CartItemPreference
        {
            public string Selected { get; set; }
            public string Title { get; set; }
            public int CartItemId { get; set; }
        }
    }
}