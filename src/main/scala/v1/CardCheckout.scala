package v1

final case class CardCheckout(itemFinder: StockFinder, offers: List[Offer] = List()) {

  def calculatePayment(items: Item*): Price = {
    val saleableItems: Seq[Product] = items.map(itemFinder)
    val discountedItems = offers.flatMap(offer => offer.find(saleableItems))
    (saleableItems ++ discountedItems).map(_.cost).foldLeft(Price(0.0))((acc, value) => acc + value)
  }

}
