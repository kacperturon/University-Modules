namespace COMP1632.Models
{
    public class Ticket
    {
        public int Id { get; set; }
        public int SeatNumber { get; set; }

        public virtual Trip Trip { get; set; }
 
        public virtual Customer Customer { get; set; }
    }
}