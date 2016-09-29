/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package ru.ltst.u2020mvp.util

object Preconditions {

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.

     * @param reference an object reference
     * *
     * @return the non-null reference that was validated
     * *
     * @throws NullPointerException if `reference` is null
     */
    @JvmStatic
    fun <T> checkNotNull(reference: T?): T {
        if (reference == null) {
            throw NullPointerException()
        }

        return reference
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.

     * @param reference an object reference
     * *
     * @param message exception message
     * *
     * @return the non-null reference that was validated
     * *
     * @throws NullPointerException if `reference` is null
     */
    @JvmStatic
    fun <T> checkNotNull(reference: T?, message: String): T {
        if (reference == null) {
            throw NullPointerException(message)
        }

        return reference
    }
}
