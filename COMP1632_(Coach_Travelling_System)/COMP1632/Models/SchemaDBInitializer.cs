using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class SchemaDBInitializer : CreateDatabaseIfNotExists<SchemaDBContext>
    {
        protected override void Seed(SchemaDBContext context)
        {

            var customers = generateCustomers();
            customers.ForEach(s => s.ShoppingCart = new ShoppingCart());      
            customers.ForEach(s => context.Customers.Add(s));
            context.SaveChanges();

            var salesStaffs = generateSalesStaffs();
            salesStaffs.ForEach(s => context.SalesStaffs.Add(s));
            context.SaveChanges();

            var customerUsers = customers.Select(u => u.User).ToList();
            var salesStaffUsers = salesStaffs.Select(u => u.User).ToList();

            var userRoles = new List<UserRole>
            {
                new UserRole()
                {
                    Role = "Customer",
                    Users = customerUsers
                },
                new UserRole()
                {
                    Role = "SalesStaff",
                    Users = salesStaffUsers
                }
            };

            userRoles.ForEach(s => context.UserRoles.Add(s));
            context.SaveChanges();

            var trips = generateTrips();
            trips.ForEach(s => context.Trips.Add(s));
            context.SaveChanges();

            var tickets = generateTickets(customers, trips);
            tickets.ForEach(s => context.Tickets.Add(s));
            context.SaveChanges();

            var reviews = generateReviews(customers, trips);
            reviews.ForEach(s => context.Reviews.Add(s));
            context.SaveChanges();

            var enquiries = generatEnquiries(customers, salesStaffs);
            enquiries.ForEach(s => context.Enquiries.Add(s));
            context.SaveChanges();

            var orders = generateOrders(customers);
            orders.ForEach(order => context.Orders.Add(order));
            context.SaveChanges();

        }

        private List<Customer> generateCustomers()
        {
            List<Customer> customers = new List<Customer>()
            {
                new Customer
                {
                    User = new User()
                    {
                        Password = "carsonPass",
                        Username = "carsonUser",
                        FirstName = "Alexander",
                        Surname = "Carson",
                    },
                    CreditCard = new CreditCard
                    {
                        CardNumber = "1234 3456 7890 1111",
                        CCV = "111",
                        ExpiryDate = new DateTime(2018, 04, 01),
                        NameOnCard = "Alexander Carson"
                    },
                    Email = "carsonalexander@gmail.com",
                },
                new Customer
                {
                    User = new User()
                    {
                        Password = "alonsoPass",
                        Username = "alonsoUser",
                        FirstName = "Meredith",
                        Surname = "Alonso"
                    },
                    CreditCard = new CreditCard()
                    {
                        CardNumber = "2222 0000 1234 6877",
                        CCV = "222",
                        ExpiryDate = new DateTime(2019, 05, 01),
                        NameOnCard = "Meredith Alonso"
                    },
                    Email = "alonsomeredith@gmail.com",
                },
                new Customer
                {
                    User = new User()
                    {
                        Password = "anandPass",
                        Username = "anandUser",
                        FirstName = "Arturo",
                        Surname = "Anand"
                    },
                    CreditCard = new CreditCard
                    {
                        CardNumber = "3333 0000 4321 4321",
                        CCV = "333",
                        ExpiryDate = new DateTime(2020, 06, 01),
                        NameOnCard = "Arturo Anand"
                    },
                    Email = "anandarturo@gmail.com",
                },
                new Customer
                {
                    User = new User()
                    {
                        Password = "barzdukasPass",
                        Username = "barzdukasPass",
                        FirstName = "Gytis",
                        Surname = "Barzdukas"
                    },
                    CreditCard = new CreditCard
                    {
                        CardNumber = "4444 0000 1212 3232",
                        CCV = "444",
                        ExpiryDate = new DateTime(2021, 07, 01),
                        NameOnCard = "Gytis Barzdukas"
                    },
                    Email = "barzdukasgytis@yahoo.com",
                },
                new Customer
                {
                    User = new User()
                    {
                        Password = "liPass",
                        Username = "liUser",
                        FirstName = "Yan",
                        Surname = "Li"
                    },
                    CreditCard = new CreditCard
                    {
                        CardNumber = "5555 2142 4320 2349",
                        CCV = "555",
                        ExpiryDate = new DateTime(2022, 08, 01),
                        NameOnCard = "Yan Li"
                    },
                    Email = "liyan@yahoo.com",
                },
                new Customer
                {
                    User = new User()
                    {
                        Password = "justicePass",
                        Username = "justiceUser",
                        FirstName = "Peggy",
                        Surname = "Justice"
                    },
                    CreditCard = new CreditCard
                    {
                        CardNumber = "6666 9102 1509 4389",
                        CCV = "666",
                        ExpiryDate = new DateTime(2023, 09, 01),
                        NameOnCard = "Peggy Justice"
                    },
                    Email = "justicepeggy@yahoo.com",
                }         
            };

            return customers;
        }

        private List<SalesStaff> generateSalesStaffs()
        {
            List<SalesStaff> salesStaffs = new List<SalesStaff>()
            {
                new SalesStaff()
                {
                    User = new User()
                    {
                        FirstName = "Bob",
                        Surname = "Malys",
                        Username = "MalysAdmin",
                        Password = "MalysPass"
                    },

                }
            };
            return salesStaffs;
        }

        private List<Trip> generateTrips()
        {
            List<Trip> trips = new List<Trip>()
            {
                new Trip
                {
                    Title = "London-Manchester",
                    Description = "Quick coach trip from the England's capital to England's almost second best city.",
                    From = "London",
                    To = "Manchester",
                    DepartureDate = new DateTime(2017, 04, 20),
                    ReturnDate = new DateTime(2017, 04, 25),
                    Price = 19.99F,
                    Coach = new Coach()
                    {
                        Capacity = 32,
                    },
                    TripImages = new List<TripImage>()
                    {
                        new TripImage()
                        {
                            ImagePath = "Content/Images/Trips/1_1.jpg"
                        }
                    }
                },
                new Trip
                {
                    Title = "Paris-London",
                    Description = "Coach from the capital of France to the capital of the United Kingdom.",
                    From = "Paris",
                    To = "London",
                    DepartureDate = new DateTime(2017, 06, 18),
                    ReturnDate = new DateTime(2017, 06, 25),
                    Price = 34.99F,
                    Coach = new Coach()
                    {
                        Capacity = 48,
                    },
                }
            };

            return trips;

        }

        private List<Ticket> generateTickets(List<Customer> customers, List<Trip> trips)
        {
            List<Ticket> tickets = new List<Ticket>()
            {
                new Ticket()
                {
                    Customer = customers[0],
                    Trip = trips[0],
                    SeatNumber = 1
                },
                new Ticket()
                {
                    Customer = customers[0],
                    Trip = trips[0],
                    SeatNumber = 2
                },
                new Ticket()
                {
                    Customer = customers[0],
                    Trip = trips[0],
                    SeatNumber = 3
                },
                new Ticket()
                {
                    Customer = customers[0],
                    Trip = trips[0],
                    SeatNumber = 4
                },
                new Ticket()
                {
                    Customer = customers[0],
                    Trip = trips[0],
                    SeatNumber = 5
                },
                new Ticket()
                {
                    Customer = customers[5],
                    Trip = trips[0],
                    SeatNumber = 6
                },
                new Ticket()
                {
                    Customer = customers[5],
                    Trip = trips[0],
                    SeatNumber = 7
                },
                new Ticket()
                {
                    Customer = customers[5],
                    Trip = trips[1],
                    SeatNumber = 9
                }
            };

            return tickets;
        }

        private List<Review> generateReviews(List<Customer> customers, List<Trip> trips)
        {
            List<Review> reviews = new List<Review>()
            {
                new Review()
                {
                    Customer = customers[0],
                    Trip = trips[0],
                    Description = "Pleasant, fast journey, will recommend to all my friends.",
                    Rating = 5
                },
                new Review()
                {
                    Customer = customers[1],
                    Trip = trips[0],
                    Description = "Quick journey, not the cheapest type of transport.",
                    Rating = 3
                },
                new Review()
                {
                    Customer = customers[1],
                    Trip = trips[1],
                    Description = "Enjoyable journey on time.",
                    Rating = 4
                },
                new Review()
                {
                    Customer = customers[2],
                    Trip = trips[1],
                    Description = "Quick journey, unhelpful staff.",
                    Rating = 3
                },
                new Review()
                {
                    Customer = customers[3],
                    Trip = trips[1],
                    Description = "Rude staff, bad quality of seats.",
                    Rating = 1
                },
                new Review()
                {
                    Customer = customers[4],
                    Trip = trips[1],
                    Description = "Staff was really unhelpful and unpleasant to be around.",
                    Rating = 1
                }
            };

            return reviews;
        }

        private List<Enquiry> generatEnquiries(List<Customer> customers, List<SalesStaff> salesStaff)
        {
            List<Enquiry> enquiries = new List<Enquiry>()
            {
                new Enquiry()
                {
                    Customer = customers[0],
                    Title = "General",
                    Question = "How long does it take for a payment to go through?"
                },
                new Enquiry()
                {
                    Customer = customers[0],
                    Title = "London-Manchester",
                    Question = "Is there a possibility to negotiate the price?"
                },
                new Enquiry()
                {
                    Customer = customers[0],
                    Title = "London-Manchester",
                    Question = "Approximately how long will be the journey?",
                    Answer = "About 4-5 hours.",
                    SalesStaff = salesStaff[0]
                },
                new Enquiry()
                {
                    Customer = customers[1],
                    Title = "London-Manchaster",
                    Question = "Are there term tickets for customers that would like to take a trip more often?"
                },
                new Enquiry()
                {
                    Customer = customers[2],
                    Title = "Paris-London",
                    Question = "Are there any toilets in coaches?"
                }
            };
            return enquiries;
        }

        private List<Order> generateOrders(List<Customer> customers)
        {
            List<Order> Orders = new List<Order>()
            {
                new Order()
                {
                    Customer = customers[0],
                    OrderItems = new List<OrderItem>()
                    {
                        new OrderItem()
                        {
                            Title = "London-Manchester",
                            From = "London",
                            To = "Manchester",
                            DepartureDate = new DateTime(2017, 04, 20),
                            ReturnDate = new DateTime(2017, 04, 25),
                            Price = 19.99F,
                            Quantity = 5,
                            TripId = 1
                        },
                    },
                    Total = 19.99F
                },
                new Order()
                {
                    Customer = customers[1],
                    OrderItems = new List<OrderItem>()
                    {
                        new OrderItem()
                        {
                            Title = "London-Manchester",
                            From = "London",
                            To = "Manchester",
                            DepartureDate = new DateTime(2017, 04, 20),
                            ReturnDate = new DateTime(2017, 04, 25),
                            Price = 19.99F,
                            Quantity = 2,
                            TripId = 1
                        },
                        new OrderItem()
                        {
                            Title = "Paris-London",
                            From = "Paris",
                            To = "London",
                            DepartureDate = new DateTime(2017, 06, 18),
                            ReturnDate = new DateTime(2017, 06, 25),
                            Price = 34.99F,
                            Quantity = 1,
                            TripId = 2
                        }
                    }
                },
                new Order()
                {
                    Customer = customers[4],
                    OrderItems = new List<OrderItem>()
                    {
                        new OrderItem()
                        {
                            Title = "London-Manchester",
                            From = "London",
                            To = "Manchester",
                            DepartureDate = new DateTime(2017, 04, 20),
                            ReturnDate = new DateTime(2017, 04, 25),
                            Price = 19.99F,
                            Quantity = 1,
                            TripId = 1
                        }
                    }
                },
                new Order()
                {
                    Customer = customers[5],
                    OrderItems = new List<OrderItem>()
                    {
                        new OrderItem()
                        {
                            Title = "Paris-London",
                            From = "Paris",
                            To = "London",
                            DepartureDate = new DateTime(2017, 06, 18),
                            ReturnDate = new DateTime(2017, 06, 25),
                            Price = 34.99F,
                            Quantity = 1,
                            TripId = 2
                        }
                    }
                }
            };

            return Orders;
        }
    }
}