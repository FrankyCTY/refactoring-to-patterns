package FormTemplateMethod;

class Loan {
	public double getCommitment() {
		return 1;
	}

	public double getUnusedPercentage() {
		return 1;
	}

	public double unusedRiskAmount() {
		return 1;
	}

	public double outstandingRiskAmount() {
		return 1;
	}
}

abstract class CapitalStrategy {
	// Template method - Refactor driven by domain knowledge
	public double capital(Loan loan) {
		return riskAmountFor(loan) * duration(loan) * riskFactorFor(loan);
	}

	public abstract double riskAmountFor(Loan loan);

	protected double duration(Loan loan) {
		return 1;
	}

	protected double riskFactorFor(Loan loan) {
		return 1;
	}

	protected double unusedRiskFactor(Loan loan) {
		return 1;
	}
}

class CapitalStrategyAdvisedLine extends CapitalStrategy {
	public double capital(Loan loan) {
		return riskAmountFor(loan) * duration(loan) * riskFactorFor(loan);
	}

	public double riskAmountFor(Loan loan) {
		return loan.getCommitment() * loan.getUnusedPercentage();
	}
}

class CapitalStrategyRevolver extends CapitalStrategy {
	public double capital(Loan loan) {
		return super.capital(loan) + unusedCapital(loan);
	}

	public double riskAmountFor(Loan loan) {
		return loan.outstandingRiskAmount();
	}

	public double unusedCapital(Loan loan) {
		return loan.unusedRiskAmount() * duration(loan) *
				unusedRiskFactor(loan);
	}
}

class CapitalStrategyTermLoan extends CapitalStrategy {
	public double capital(Loan loan) {
		return riskAmountFor(loan) * duration(loan) * riskFactorFor(loan);
	}

	public double riskAmountFor(Loan loan) {
		return loan.getCommitment();
	}

	protected double duration(Loan loan) {
		return weightedAverageDuration(loan);
	}

	private double weightedAverageDuration(Loan loan) {
		return 1;
	}
}