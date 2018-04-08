package v1

final case class Price(amount: BigDecimal) {
  def negated = Price(-amount)

  def +(other: Price) = Price(amount + other.amount)

  def -(other: Price) = Price(amount - other.amount)

  def *(n: Int) = Price(amount * n)

  def format: String = f"£${amount}%2.2f"
}