using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class AnswerEnquiryViewModel
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Question { get; set; }
        [Required]
        [DataType(DataType.MultilineText)]
        public string Answer { get; set; }

        public Customer Customer { get; set; }
        public SalesStaff SalesStaff { get; set; }
    }
}