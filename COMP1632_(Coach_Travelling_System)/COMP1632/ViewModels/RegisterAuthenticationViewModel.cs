using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.ViewModels
{
    public class RegisterAuthenticationViewModel
    {
        public int CustomerId { get; set; }

        [Required]
        [Display(Name="Username")]
        public string Username { set; get; }

        [Required]
        [DataType(DataType.Password)]
        public string Password { set; get; }

        [Required]
        [Display(Name = "First Name")]
        public string FirstName { set; get; }

        [Required]
        public string Surname { set; get; }

        [Required]
        [DataType(DataType.EmailAddress)]
        public string Email { get; set; }

        [Required]
        [Display(Name="Name on card")]
        public string NameOnCard { get; set; }

        [Required]
        [DataType(DataType.CreditCard), DisplayFormat(ApplyFormatInEditMode = true)]
        [Display(Name = "Card Number")]
        public string CardNumber { get; set; }

        [Required]
        [DataType(DataType.Date),DisplayFormat(DataFormatString = "{0:MMM-yyyy}", ApplyFormatInEditMode=true)]
        [Display(Name = "Expiry Date")]
        public DateTime ExpiryDate { get; set; }

        [Required]
        public string CCV { get; set; }


    }
}