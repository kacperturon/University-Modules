using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class IndexShoppingCartViewModel
    {
        public ShoppingCart ShoppingCart { get; set; }

        [DisplayFormat(DataFormatString = "{0:C2}")]
        public float Total { get; set; }
        

    }
}