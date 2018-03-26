package shopping

import shopping.Stock.{BuyNGetN, Offer, StockItem}

object Stock {

  case class StockItem(name: String, price: Double)

  case class Offer(name: String, discount: BuyNGetN)

  case class BuyNGetN(buyNItems: Int, getNItems: Int)

}

class Stock {
  private val stock = List(StockItem("apple", 0.60), StockItem("orange", 0.25))
  private val offers: List[Offer] = List()

  def getStockItem(item: String): Option[StockItem] = stock.find(_.name == item)

  def getOffer(item: StockItem): Option[Offer] = offers.find(_.name == item.name)


}