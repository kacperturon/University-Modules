using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class Enquiry
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Question { get; set; }
        public string Answer { get; set; }

        public Customer Customer { get; set; }
        public SalesStaff SalesStaff { get; set; }

    }
}