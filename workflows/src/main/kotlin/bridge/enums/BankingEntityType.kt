package com.cbdc.industria.tech.bridge.enums

import net.corda.v5.base.annotations.CordaSerializable

@CordaSerializable
enum class BankingEntityType {
    PAYMENT_INTERFACE_PROVIDER, COMMERCIAL_BANK
}
