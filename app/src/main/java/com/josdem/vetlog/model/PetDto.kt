package com.josdem.vetlog.model

/**
 * Data transfer object representing a collection of pet IDs.
 *
 * @property petsIds List of unique identifiers for pets. Used to transfer pet references in API requests and responses.
 */
data class PetDto(
    val petsIds: List<Long>,
)
