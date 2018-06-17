using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class Coach
    {
        [Key, ForeignKey("Trip")]
        public int TripId { get; set; }

        public int Capacity { get; set; }

        public virtual Trip Trip { get; set; }
    }
}