using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1551
{
    public class Payment
    {
        private static int invoiceNumber = 0; //Increments with every new payment
        private string confirmationNumber; //Random value from 1 - 9999999999 in format 0000000000
        private double vAT = 0.2;
        private double discountPercentage;
        private double discount; //Discount value in specified currency
        private string cultureInfo; //Used for formatting strings e.g. GBP - 'en-GB'
        private int peopleCount; 
        private string currency; //Currency acronym GBP, EUR, USD, AUD
        private double flatRatePerPerson; //Rate before discounts, vat etc. for one person
        private double flatRate; //Rate multiplied by the amount of people
        private double fee; //Total value to pay

        public Payment() //Default values
        {
            this.peopleCount = 0;
            this.currency = "GBP";
            initValues();
            invoiceNumber++;
        }

        public Payment(int peopleCount, string currency)
        {
            this.peopleCount = peopleCount;
            this.currency = currency;
            initValues();
            invoiceNumber++;
        }

        private void initValues() //Calculate all payment values
        {
            this.discountPercentage = calculateDiscount();
            this.flatRatePerPerson = calculateFlatRatePerPerson();
            this.flatRate = flatRatePerPerson * peopleCount;
            this.fee = calculateFees();
            this.confirmationNumber = new Random().Next(0, 999999999).ToString("000000000");
            if (currency == "GBP")
            {
                cultureInfo = "en-GB";
            }
            if (currency == "EUR")
            {
                cultureInfo = "de-DE";
            }
            if (currency == "USD")
            {
                cultureInfo = "en-US";
            }
            if (currency == "AUD")
            {
                cultureInfo = "en-AU";
            }
        }

        private double calculateFees() //Calculate total fee
        {
            double fee;
            discount = flatRate*discountPercentage;
            fee = flatRate - discount;
            double VATRate = fee*VAT;
            fee = fee + VATRate;
            return fee;
        }

        private double calculateDiscount() //Calculate % discount depending on amount of children
        {
            double discount = 0.0;
            if (this.peopleCount > 1)
            {
                discount = 0.05;
            }
            if (this.peopleCount > 4)
            {
                discount = 0.10;
            }
            if (this.peopleCount > 9)
            {
                discount = 0.15;
            }
            
            return discount;
        }

        private double calculateFlatRatePerPerson() //Conversion between different currencies
        {
            //Default value 200 GBP
            double defaultRate = 200; //in GBP

            if (currency == "EUR")
            {
                defaultRate *= 1.19; // 1 GBP = 1.19 EUR
            }
            if (currency == "USD")
            {
                defaultRate *= 1.27; // 1 GBP = 1.27 USD
            }
            if (currency == "AUD")
            {
                defaultRate *= 1.71; // 1 GBP = 1.71 AUD
            }
            return defaultRate;
        }

        public double VAT
        {
            get { return vAT; }
        }

        public double DiscountPercentage
        {
            get { return discountPercentage; }
        }

        public int PeopleCount
        {
            set
            {
                peopleCount = value;
                initValues();
            } 
            get { return peopleCount; }
        }

        public double FlatRate
        {
            get { return flatRate; }
        }

        public double Fee
        {
            get { return fee; }
        }

        public string Currency
        {
            get { return currency; }
            set
            {
                currency = value;
                initValues();
            }
        }

        public double Discount
        {
            get { return discount; }
        }

        public double FlatRatePerPerson
        {
            get { return flatRatePerPerson; }
            set
            {
                flatRatePerPerson = value;
                initValues();
            }
        }

        public string CultureInfo
        {
            get { return cultureInfo; }
        }

        public static int InvoiceNumber
        {
            get { return invoiceNumber; }
        }

        public string ConfirmationNumber
        {
            get { return confirmationNumber; }
        }
    }
}