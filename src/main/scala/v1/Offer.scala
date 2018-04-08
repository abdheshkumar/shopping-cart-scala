package v1

sealed trait Offer {
  def find(items: Seq[Product]): Seq[Product]
}


final case class BuyNCheaper(product: Product, n: Int, priceForN: Price) extends Offer {
  val discount = product.cost * n - priceForN
  val discountItem: Product = Product(product.sku,s"Buy $n ${product.item}s for ${priceForN.format}, means a discount of ${discount.format}" , discount.negated)

  override def find(items: Seq[Product]): List[Product] = {
    val theseItems = items.filter(_ == product)
    val numberOfOffers = theseItems.size / n
    List.fill(numberOfOffers)(discountItem)
  }

}