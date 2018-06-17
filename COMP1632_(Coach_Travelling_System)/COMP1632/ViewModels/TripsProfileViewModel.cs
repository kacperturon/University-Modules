using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class TripsProfileViewModel
    {
        public List<Trip> Trips { get; set; }
        public List<Ticket> Tickets { get; set; }
    }
}