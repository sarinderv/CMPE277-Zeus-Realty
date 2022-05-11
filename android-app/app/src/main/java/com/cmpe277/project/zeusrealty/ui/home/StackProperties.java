package com.cmpe277.project.zeusrealty.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class StackProperties implements Parcelable {

        /**
         * fields
         */
        private String name;
        private String category;
        private String country;
    private String address;
    private String imageURL;
    private String count;
    private String about;
    private String price;
    private String total_area;
    private String living_area;

        /**
         * Constructor.
         */
        public StackProperties() {
            super();
        }

        /**
         * Constructor to initialize fields.
         *
         * @param parcel
         */
        public StackProperties(Parcel parcel) {

         //   imgUrlThumbMul = (ArrayList<String>) parcel.readSerializable();
          //  imgUrlFullMul = (ArrayList<String>) parcel.readSerializable();
             name=parcel.readString();
             category=parcel.readString();
             country=parcel.readString();
             address=parcel.readString();
             imageURL=parcel.readString();
             count= parcel.readString();;
             about=parcel.readString();
             price=parcel.readString();
             total_area=parcel.readString();
             living_area=parcel.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        /**
         * Method used to write the values of the fields to the parcel.
         *
         * @param parcel
         * @param flag
         */
        @Override
        public void writeToParcel(Parcel parcel, int flag) {
            parcel.writeString(name);
            parcel.writeString(category);
            parcel.writeString(country);
            parcel.writeString(address);
            parcel.writeString(imageURL);
            parcel.writeString(count);
            parcel.writeString(about);
            parcel.writeString(price);
            parcel.writeString(total_area);
            parcel.writeString(living_area);
        }

        /**
         * Parcelable for StackProduits class objects.
         */
        public static Parcelable.Creator<StackProperties> CREATOR = new Parcelable.Creator<StackProperties>() {
            @Override
            public StackProperties createFromParcel(Parcel source) {
                return new StackProperties(source);
            }

            @Override
            public StackProperties[] newArray(int size) {
                return new StackProperties[size];
            }
        };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_area() {
        return total_area;
    }

    public void setTotal_area(String total_area) {
        this.total_area = total_area;
    }

    public String getLiving_area() {
        return living_area;
    }

    public void setLiving_area(String living_area) {
        this.living_area = living_area;
    }
}
