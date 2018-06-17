using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Microsoft.Ajax.Utilities;

namespace COMP1632.Models
{
    public partial class ShoppingCart
    {
        private SchemaDBContext db = new SchemaDBContext();

        public void AddToCart(int tripId, int quantity)
        {
            var shoppingCart = db.ShoppingCarts.FirstOrDefault(cart => cart.Id == Id);

            Trip trip = db.Trips.FirstOrDefault(u => u.Id.Equals(tripId));
            if (shoppingCart.CartItems.All(t => t.Trip.Id != tripId))
                //Check if this trip isn't already in the shopping cart
            {
                System.Diagnostics.Debug.WriteLine("This trip doesn't exist");

                shoppingCart.CartItems.Add(new CartItem()
                {
                    Trip = trip,
                    Quantity = quantity
                });

                db.ShoppingCarts.AddOrUpdate(shoppingCart);
                db.SaveChanges();
            }
            else
            {
                System.Diagnostics.Debug.WriteLine("This trip already exists");
                var cartItem = shoppingCart.CartItems.FirstOrDefault(t => t.Trip.Id == tripId);
                System.Diagnostics.Debug.WriteLine(cartItem.Trip.Title);

                cartItem.Quantity += quantity;


                db.CartItems.AddOrUpdate(cartItem);
                db.SaveChanges();

            }
        }

        public void ClearCart()
        {
            var shoppingCart = db.ShoppingCarts.FirstOrDefault(cart => cart.Id == Id);

            shoppingCart.CartItems.Clear();
            db.ShoppingCarts.AddOrUpdate(shoppingCart);
            db.SaveChanges();
        }

        public float GetTotal()
        {
            var shoppingCart = db.ShoppingCarts.FirstOrDefault(cart => cart.Id == Id);

            float total = 0.0f;
            shoppingCart.CartItems.ForEach(item => total += item.Quantity * item.Trip.Price);

            return total;
        }

        public Order CreateOrder()
        {
            var shoppingCart = db.ShoppingCarts.FirstOrDefault(cart => cart.Id == Id);
            var customer = shoppingCart.Customer;

            List<Ticket> tickets = new List<Ticket>();
            List<OrderItem> orderItems = new List<OrderItem>();
            foreach (var cartItem in shoppingCart.CartItems)
            {
                //Generate Tickets
                List<int> seatNumbers = RandomSeats(cartItem);
                System.Diagnostics.Debug.WriteLine("seasts" + "  "+ seatNumbers.Count);
                for (int i = 0; i < cartItem.Quantity; i++)
                {
                    tickets.Add(new Ticket()
                    {
                        Customer = customer,
                        Trip = cartItem.Trip,
                        SeatNumber = seatNumbers[i]
                    });
                }

                orderItems.Add(new OrderItem()
                {
                    Title = cartItem.Trip.Title,
                    From = cartItem.Trip.From,
                    To = cartItem.Trip.To,
                    DepartureDate = cartItem.Trip.DepartureDate,
                    ReturnDate = cartItem.Trip.ReturnDate,
                    Price = cartItem.Trip.Price,
                    Quantity = cartItem.Quantity,
                    TripId = cartItem.Trip.Id,
                    SeatNumbers = seatNumbers
                });                
            }

            Order order = new Order()
            {
                Customer = customer,
                OrderItems = orderItems,
                Total = shoppingCart.GetTotal()
            };

            if (AreTicketsAvailable())
            {
                tickets.ForEach(ticket => db.Tickets.AddOrUpdate(ticket));
                db.SaveChanges();
                db.Orders.AddOrUpdate(order);
                db.SaveChanges();
                shoppingCart.ClearCart();
                return order;

            }
            return null;
        }

        public bool AreTicketsAvailable()
        {
            var shoppingCart = db.ShoppingCarts.FirstOrDefault(cart => cart.Id == Id);

            var isAvailable = true;
            foreach (var cartItem in shoppingCart.CartItems)
            {
                var Trip = db.Trips.Find(cartItem.Trip.Id);
                if ((Trip.Coach.Capacity-Trip.Tickets.Count) < cartItem.Quantity) isAvailable = false;
            }
            return isAvailable;
        }

        private List<int> RandomSeats(CartItem cartItem)
        {
            List<int> seatNumbers = new List<int>();
            int seat =0;
            switch (cartItem.SeatPreference)
            {
                case 1: //adjacent
                    int startingValue = 1;
                    int largestCount = 0;
                    for (int i = 1; i < cartItem.Trip.Coach.Capacity+1; i++)
                    {
                        if (largestCount == cartItem.Quantity) break;
                        if (!SeatTaken(i))
                        {
                            largestCount++;
                        }
                        else
                        {
                            largestCount = 0;
                            startingValue = i+1;
                        }
                    }
                    System.Diagnostics.Debug.WriteLine("Starting " +startingValue+" count " +largestCount);
                    System.Diagnostics.Debug.WriteLine("cartitems " + cartItem.Quantity);

                    for (int i = 0; i < cartItem.Quantity; i++)
                    {
                        if (!SeatTaken(startingValue) || seatNumbers.Contains(startingValue))
                        {
                            seatNumbers.Add(startingValue);
                            startingValue++;
                        }
                    }
                    System.Diagnostics.Debug.WriteLine("seats generated: " + seatNumbers.Count);

                    break;
                case 2: //middle
                    seat = 2;
                    break;
                case 3: //window
                    seat = 1;
                    break;
            }
            if(cartItem.SeatPreference==2 ||cartItem.SeatPreference == 3)
            {
                for (int i = 0; i < (cartItem.Trip.Coach.Capacity / 2); i++)
                {
                    if (cartItem.Quantity == seatNumbers.Count) break;
                    if (!SeatTaken(seat) || seatNumbers.Contains(seat))
                    {
                        seatNumbers.Add(seat);
                        if (seat % 2 == 0) seat += 1;
                        else seat += 3;
                    }
                }
            }

            if (seatNumbers.Count < cartItem.Quantity) //The rest filled with random seat numbers
            {
                System.Diagnostics.Debug.WriteLine("RANDOM " + " " + cartItem.Quantity + " " + seatNumbers.Count);
                int randomTickets = cartItem.Quantity - seatNumbers.Count;
                for (int i = 0; i < randomTickets; i++)
                {
                    System.Diagnostics.Debug.WriteLine("RANDOM " + " " + i);
                    seatNumbers.Add(RandomSeat(cartItem, seatNumbers));
                }
            }

            return seatNumbers;
        }

        private int RandomSeat(CartItem cartItem, List<int> seatNumbers)
        {
            int randomTicket = new Random().Next(1, cartItem.Trip.Coach.Capacity);
            while (SeatTaken(randomTicket) || seatNumbers.Contains(randomTicket))
            {
                randomTicket = new Random().Next(1, cartItem.Trip.Coach.Capacity);
            }
            return randomTicket;
        }

        private bool SeatTaken(int seatNumber)
        {
            bool seatTaken = db.Tickets.Any(ticket => ticket.SeatNumber == seatNumber);

            return seatTaken;
        }


    }
}