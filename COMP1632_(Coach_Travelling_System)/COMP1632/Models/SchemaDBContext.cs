using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class SchemaDBContext : DbContext
    {
        public SchemaDBContext()
        {
            Database.SetInitializer(new SchemaDBInitializer());

        }
        public DbSet<UserRole> UserRoles { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<SalesStaff> SalesStaffs { get; set; }
        public DbSet<Customer> Customers { get; set; }
        public DbSet<CreditCard> CreditCards { get; set; }
        public DbSet<Enquiry> Enquiries { get; set; }

        public DbSet<Trip> Trips { get; set; }
        public DbSet<TripImage> TripImages { get; set; }
        public DbSet<Coach> Coaches { get; set; }
        public DbSet<Ticket> Tickets { get; set; }
        public DbSet<Review> Reviews { get; set; }

        public DbSet<CartItem> CartItems { get; set; }
        public DbSet<ShoppingCart> ShoppingCarts { get; set; }
        public DbSet<OrderItem> OrderItems { get; set; }
        public DbSet<Order> Orders { get; set; }


    }
}