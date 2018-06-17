namespace COMP1632.Models
{
    public class Review
    {
        public int Id { get; set; }
        public int Rating { get; set; }
        public string Description { get; set; }
        public virtual Customer Customer { get; set; }
        public virtual Trip Trip { get; set; }
    }
}