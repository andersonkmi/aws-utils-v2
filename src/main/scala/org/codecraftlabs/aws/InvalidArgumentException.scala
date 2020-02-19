package org.codecraftlabs.aws

case class InvalidArgumentException(private val message: String = "",
                                    private val cause: Throwable = None.orNull) extends Exception (message, cause)
