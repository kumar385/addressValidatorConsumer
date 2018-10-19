package com.address.consumer.addressValidatorConsumer.api

import com.address.consumer.addressValidatorConsumer.data.Address
import com.address.consumer.addressValidatorConsumer.data.UserProfile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate
import org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids=["com.producer:addressValidator:0.0.1-SNAPSHOT:stubs:6565"],stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@ActiveProfiles("test")
class ProfileValidationApiTest{

    private val subject=ProfileValidationApi(RestTemplate(),"http://localhost:6565/api/validateAddress")

    @Test
    fun `should return true for validation`(){
        val actualResponse=subject.validateProfile(UserProfile(name="test",address = Address(addresssLine1 = "1234 abc",city = "palo",zipCode = "12345",state = "AF")))
        assertThat(actualResponse).isTrue()
    }
}