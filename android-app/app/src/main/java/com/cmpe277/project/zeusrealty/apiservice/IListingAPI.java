package com.cmpe277.project.zeusrealty.apiservice;

import com.cmpe277.project.zeusrealty.model.LocationAPIResponse;
import com.cmpe277.project.zeusrealty.model.PropertyListingAPIResponse;
import com.cmpe277.project.zeusrealty.model.PropertyListingAPIResponseContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface IListingAPI {
        @GET("/locations/point")
        Call<List<LocationAPIResponse>> getLocationDetailsatCenter(@Query("lat") String lat,@Query("long") String longitude);
        @GET("/locations/bounds")
        Call<List<LocationAPIResponse>> getLocationDetailsInsideRectangle(@Query("bounds") String bounds);
        @GET("/listings/byproperty/{id}")
        Call<PropertyListingAPIResponseContainer> getLocationDetailsById(@Path(value="id") String propertyid);

        @GET("/listings")
        Call<List<LocationAPIResponse>> getPropertyListings();

}
