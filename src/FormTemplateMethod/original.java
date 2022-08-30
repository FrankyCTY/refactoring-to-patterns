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
	public abstract double capital(Loan loan);

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
		return loan.getCommitment() * loan.getUnusedPercentage() * duration(loan) * riskFactorFor(loan);
	}
}

class CapitalStrategyRevolver extends CapitalStrategy {
	public double capital(Loan loan) {
		return loan.outstandingRiskAmount() * duration(loan) * riskFactorFor(loan)
				+ loan.unusedRiskAmount() * duration(loan) * unusedRiskFactor(loan);
	}
}

class CapitalStrategyTermLoan extends CapitalStrategy {
	public double capital(Loan loan) {
		return loan.getCommitment() * duration(loan) * riskFactorFor(loan);
	}

	protected double duration(Loan loan) {
		return weightedAverageDuration(loan);
	}

	private double weightedAverageDuration(Loan loan) {
		return 1;
	}
}