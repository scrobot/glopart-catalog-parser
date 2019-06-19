package com.github.glopartparser.parsing

import io.circe.generic.JsonCodec

@JsonCodec
case class Category()

@JsonCodec
case class Price()

@JsonCodec
case class Product()