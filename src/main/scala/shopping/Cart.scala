package shopping

import scala.collection.mutable.ListBuffer

case class Cart(stockService: Stock){
  private val cart = ListBuffer[Item]()

  def totalBill: (Double, Double) = {
    val result: List[(Double, Double)] = cart.groupBy(_.name).toList.flatMap {
      case (item, list) =>
        calculateItemPrice(item, list.size)
    }
    result.foldLeft((0.0, 0.0)) {
      case ((total, discount), (calculatedPrice, discountPrice)) =>
        (total + calculatedPrice, discount + discountPrice)
    }
  }

  def addItem(item: Item): Unit = cart += item

  /**
    * This function calculate price of item after applying discount offer
    *
    * @param item : shopping.Item item name and price
    * @return Double final price of item after applying discount offer
    * @example { { {
    *          //Discount-3 for the price of 2 on Oranges, oranges cost 25p
    *          //calculate price of 5 oranges
    *          (2*(5/3)+(5%3))*0.25
    *          }}}
    **/
  def calculateItemPrice(item: String, numberOfItems: Int): Option[(Double, Double)] = {
    stockService.getStockItem(item).map {
      stockItem =>
        stockService.getOffer(stockItem).fold((numberOfItems * stockItem.price, 0D)) {
          offer =>
            val discount = offer.discount
            val priceFor = (discount.buyNItems * (numberOfItems / discount.getNItems) + (numberOfItems % discount.getNItems))
            (priceFor * stockItem.price, (numberOfItems - priceFor) * stockItem.price)
        }

    }
  }
}

object Cart extends Cart(new Stock)