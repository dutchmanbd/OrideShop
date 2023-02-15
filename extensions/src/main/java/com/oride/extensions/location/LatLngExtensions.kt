package com.oride.extensions.location

import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import com.oride.utilities.AddressInfo


private const val UNKNOWN = "Unknown"

fun LatLng.toAddressInfo(geoCoder: Geocoder): AddressInfo = try {
    val addresses = geoCoder.getFromLocation(latitude, longitude, 1)
    if (addresses.isNullOrEmpty()) {
        AddressInfo()
    } else {
        val address = addresses[0]
        var addressLine1 = ""
        addressLine1 = getName(address.getAddressLine(0))

        if(addressLine1.isEmpty()){
            addressLine1 = getName(address.featureName).plus(",")
            addressLine1 += getName( address.premises).plus(",")
            addressLine1 += getName(address.adminArea ?: address.subAdminArea).plus(",")
            addressLine1 += getName(address.thoroughfare ?: address.subThoroughfare).plus(",")
            addressLine1 += getName(address.locality ?: address.subLocality).plus(",")
            addressLine1 += address.postalCode ?: ""
        }

        AddressInfo(
            cityName = getName(address.subLocality),
            stateName = getName(address.locality),
            countryName = getName(address.countryName),
            posterCode = getName(address.postalCode),
            addressLine1 = addressLine1,
            addressLine2 = addressLine1
        )
    }
} catch (e: Exception) {
    AddressInfo()
}

private fun getName(name: String?) = if(name.isNullOrEmpty()) "" else name