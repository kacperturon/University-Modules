using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace COMP1632.ViewModels
{
    public class NewEnquiryViewModel
    {
        public IEnumerable<SelectListItem> TitleOptions { get; set; }
        [Required(ErrorMessage = "Selection is required.")]
        public string SelectedTitleOption { get; set; }
        public int TripId { get; set; }
        [Required]
        [DataType(DataType.MultilineText)]
        public string Question { get; set; }
    }
}