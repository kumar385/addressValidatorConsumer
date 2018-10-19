package com.address.consumer.addressValidatorConsumer.api

import com.address.consumer.addressValidatorConsumer.data.Address
import com.address.consumer.addressValidatorConsumer.data.AddressError
import com.address.consumer.addressValidatorConsumer.data.UserProfile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.apache.catalina.manager.StatusTransformer.setContentType
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


@RestController
@RequestMapping("profile")
class ProfileValidationApi(@Autowired val restTemplate: RestTemplate,
                           @Value("\${address.validationURL}")
                           val addressValidationURL: String
) {

    @PostMapping("api/profileValidation")
    fun validateProfile(@RequestBody userProfile: UserProfile): Boolean {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = HttpEntity<Address>(userProfile.address, headers)
        return userProfile.name.isNotEmpty() && restTemplate.postForEntity(addressValidationURL, entity, AddressError::class.java).body?.validAddress==true
    }

}