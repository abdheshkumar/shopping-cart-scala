package v1

class ItemNotFoundException(item: Item) extends Exception(s"Item [$item] was not found")

/** I expect the actual 'key' to be a bar code'.*/
final case class StockFinder(nameToSaleableItem: Map[Item, Product]) extends (Item => Product) {
  def apply(item: Item) = nameToSaleableItem.getOrElse(item, throw new ItemNotFoundException(item))
}
