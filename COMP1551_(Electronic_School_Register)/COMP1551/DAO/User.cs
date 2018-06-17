using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1551.DAO
{
    abstract public class User //Basic class that all users inherit from
    {
        public abstract int ID { get; }
        public abstract string Username { get; set; }
        public abstract string Password { get; set; }
        public abstract int Accessibility { get; }
    }
}