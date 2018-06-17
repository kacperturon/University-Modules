using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class IndexHomeViewModel
    {
        public List<TripInfo> TripsInfo { get; set; }

        public string From { get; set; }
        public string To { get; set; }
        public float PriceMin { get; set; }
        public float PriceMax { get; set; }

        public class TripInfo
        {
            public Trip Trip { get; set; }
            public int TicketsLeft { get; set; }
        }

    }
}