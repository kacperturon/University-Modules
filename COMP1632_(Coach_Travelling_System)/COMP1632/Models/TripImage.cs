using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class TripImage
    {
        public int Id { get; set; }
        public string ImagePath { get; set; }
        public virtual Trip Trip { get; set; }
    }
}