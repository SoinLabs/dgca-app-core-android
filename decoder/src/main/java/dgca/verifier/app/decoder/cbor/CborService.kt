/*
 *  ---license-start
 *  eu-digital-green-certificates / dgca-verifier-app-android
 *  ---
 *  Copyright (C) 2021 T-Systems International GmbH and all other contributors
 *  ---
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ---license-end
 *
 *  Created by Mykhailo Nester on 4/23/21 9:50 AM
 */

package dgca.verifier.app.decoder.cbor

import dgca.verifier.app.decoder.model.GreenCertificate
import dgca.verifier.app.decoder.model.VerificationResult
import java.time.ZonedDateTime
import java.util.*

data class GreenCertificateData(
    val issuingCountry: String?,
    val hcertJson: String,
    val greenCertificate: GreenCertificate,
    val issuedAt: Date,
    val expirationTime: Date
) {

    fun getNormalizedIssuingCountry(): String =
        (if (this.issuingCountry?.isNotBlank() == true) {
            this.issuingCountry
        } else {
            this.greenCertificate.getIssuingCountry()
        }).toLowerCase(Locale.ROOT)
}

/**
 * Decodes input as a CBOR structure
 */
interface CborService {

    fun decode(input: ByteArray, verificationResult: VerificationResult): GreenCertificate?

    fun decodeData(input: ByteArray, verificationResult: VerificationResult): GreenCertificateData?

    fun getPayload(input: ByteArray): ByteArray?
}