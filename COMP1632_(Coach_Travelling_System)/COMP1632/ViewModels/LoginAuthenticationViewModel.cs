using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.ViewModels
{
    public class LoginAuthenticationViewModel
    {
        [Required]
        public string Username { set; get; }
        [Required]
        [DataType(DataType.Password)]
        public string Password { set; get; }
    }
}