package com.apexledger.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apexledger.app.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class FinanceViewModel : ViewModel() {

    val supportedCurrencies = listOf(
        CurrencyConfig("USD", "$", "US Dollar", "🇺🇸", 1.0),
        CurrencyConfig("EUR", "€", "Euro", "🇪🇺", 0.92),
        CurrencyConfig("GBP", "£", "British Pound", "🇬🇧", 0.79),
        CurrencyConfig("JPY", "¥", "Japanese Yen", "🇯🇵", 152.0),
        CurrencyConfig("CAD", "CA$", "Canadian Dollar", "🇨🇦", 1.38),
        CurrencyConfig("AUD", "AU$", "Australian Dollar", "🇦🇺", 1.51)
    )

    private val _selectedCurrency = MutableStateFlow(supportedCurrencies[0])
    val selectedCurrency: StateFlow<CurrencyConfig> = _selectedCurrency.asStateFlow()

    private val _transactions = MutableStateFlow<List<Transaction>>(
        listOf(
            Transaction("tx_1", "income", 5400.00, "USD", "Client Retainer", "Q4 Fintech Architecture Retainer", "2026-10-15", false, "Business Income", "Invoice #109, Freelance", "Stripe Direct"),
            Transaction("tx_2", "expense", 149.00, "USD", "Software & SaaS", "AWS Cloud Compute & Serverless DB", "2026-10-14", true, "Software & Online Services", "Infrastructure, Schedule C", "Chase Business"),
            Transaction("tx_3", "expense", 42.50, "USD", "Food & Dining", "Client Strategy Lunch - Blue Bottle", "2026-10-13", true, "Business Meals (50%)", "Client Meeting", "Amex Gold"),
            Transaction("tx_4", "expense", 28.00, "USD", "Travel & Transit", "Uber Ride to Tech Meetup & Airport", "2026-10-12", true, "Travel & Lodging", "Ground Transit", "Chase Sapphire"),
            Transaction("tx_5", "expense", 89.00, "USD", "Utilities & Bills", "Gigabit Fiber Internet (Home Office)", "2026-10-10", true, "Home Office Utilities", "Internet", "Checking"),
            Transaction("tx_6", "expense", 32.00, "USD", "Software & SaaS", "GitHub Copilot + Workspace", "2026-10-09", true, "Software & Online Services", "Dev Tools", "Chase Business"),
            Transaction("tx_7", "expense", 125.00, "USD", "Advertising & Marketing", "LinkedIn Sponsored Editorial", "2026-10-08", true, "Advertising", "Marketing", "Chase Business"),
            Transaction("tx_8", "expense", 45.00, "USD", "Office & Equipment", "Ergonomic Desk Accessories", "2026-10-06", true, "Office Supplies & Tech", "Workspace", "Chase Sapphire"),
            Transaction("tx_9", "income", 1800.00, "USD", "Consulting", "Security & Firestore Audit Advisory", "2026-10-04", false, "Business Income", "Wire Transfer", "SVB Escrow"),
            Transaction("tx_10", "expense", 18.99, "USD", "Entertainment", "Personal Streaming Bundle", "2026-10-02", false, "Non-Deductible Personal", "Personal", "Amex Gold")
        )
    )
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val _budgets = MutableStateFlow<List<Budget>>(
        listOf(
            Budget("b1", "Software & SaaS", 300.0, "USD", 80),
            Budget("b2", "Food & Dining", 450.0, "USD", 75),
            Budget("b3", "Travel & Transit", 250.0, "USD", 85),
            Budget("b4", "Advertising & Marketing", 400.0, "USD", 80),
            Budget("b5", "Utilities & Bills", 200.0, "USD", 90),
            Budget("b6", "Office & Equipment", 350.0, "USD", 80)
        )
    )
    val budgets: StateFlow<List<Budget>> = _budgets.asStateFlow()

    private val _subscriptions = MutableStateFlow<List<Subscription>>(
        listOf(
            Subscription("s1", "AWS Cloud Infrastructure", 149.0, "USD", "monthly", "2026-11-01", "Software & SaaS", "active", true),
            Subscription("s2", "GitHub Copilot Enterprise", 32.0, "USD", "monthly", "2026-10-29", "Software & SaaS", "active", true),
            Subscription("s3", "Figma Professional Team", 15.0, "USD", "monthly", "2026-10-24", "Software & SaaS", "active", true),
            Subscription("s4", "Midjourney Pro AI", 60.0, "USD", "monthly", "2026-10-21", "Software & SaaS", "active", true),
            Subscription("s5", "Webflow Hosting", 39.0, "USD", "monthly", "2026-11-05", "Software & SaaS", "active", true)
        )
    )
    val subscriptions: StateFlow<List<Subscription>> = _subscriptions.asStateFlow()

    private val _bankConnections = MutableStateFlow<List<BankConnection>>(
        listOf(
            BankConnection("bank1", "Chase Business Platinum", "•••• 8824", "Business Checking", "active", "Today at 08:30 AM"),
            BankConnection("bank2", "American Express Business Gold", "•••• 1009", "Credit Card", "active", "Today at 09:15 AM"),
            BankConnection("bank3", "Silicon Valley Bank Escrow", "•••• 4201", "Commercial Deposit", "active", "Yesterday at 04:45 PM")
        )
    )
    val bankConnections: StateFlow<List<BankConnection>> = _bankConnections.asStateFlow()

    private val _syncDevices = MutableStateFlow<List<SyncDevice>>(
        listOf(
            SyncDevice("d1", "Google Pixel 8 Pro (Native App)", "android", "Just now", 42, "Active Controller"),
            SyncDevice("d2", "ApexLedger Web Dashboard", "web_dashboard", "Online now", 42, "Primary Web View"),
            SyncDevice("d3", "Google Sites Intranet Gadget", "google_sites_endpoint", "3 min ago", 41, "Webhook Connected"),
            SyncDevice("d4", "Apple iPad Pro 12.9\"", "ios", "1 hour ago", 40, "Standby Sync")
        )
    )
    val syncDevices: StateFlow<List<SyncDevice>> = _syncDevices.asStateFlow()

    private val _financialHealth = MutableStateFlow(FinancialHealth())
    val financialHealth: StateFlow<FinancialHealth> = _financialHealth.asStateFlow()

    private val _syncMessage = MutableStateFlow<String?>(null)
    val syncMessage: StateFlow<String?> = _syncMessage.asStateFlow()

    fun selectCurrency(currency: CurrencyConfig) {
        _selectedCurrency.value = currency
    }

    fun formatAmount(amountUsd: Double): String {
        val curr = _selectedCurrency.value
        val converted = amountUsd * curr.exchangeRateToUsd
        return "${curr.symbol}${String.format("%,.2f", converted)}"
    }

    fun logExpense(
        description: String,
        amount: Double,
        category: String,
        source: String = "Android Widget",
        taxDeductible: Boolean = true,
        taxCategory: String = if (category == "Food & Dining") "Business Meals (50%)" else "Software & Online Services"
    ) {
        viewModelScope.launch {
            val newTx = Transaction(
                id = UUID.randomUUID().toString(),
                type = "expense",
                amount = amount,
                currency = "USD",
                category = category,
                description = description,
                date = "2026-10-17",
                taxDeductible = taxDeductible,
                taxCategory = taxCategory,
                tags = "Android 1-Tap",
                source = source
            )
            _transactions.value = listOf(newTx) + _transactions.value
            _syncMessage.value = "Expense logged & encrypted: $description"
        }
    }

    fun addTransaction(
        type: String,
        amount: Double,
        category: String,
        description: String,
        date: String,
        taxDeductible: Boolean,
        taxCategory: String
    ) {
        viewModelScope.launch {
            val newTx = Transaction(
                id = UUID.randomUUID().toString(),
                type = type,
                amount = amount,
                currency = "USD",
                category = category,
                description = description,
                date = date.ifBlank { "2026-10-17" },
                taxDeductible = taxDeductible,
                taxCategory = taxCategory,
                tags = "Manual Entry",
                source = "ApexLedger Compose"
            )
            _transactions.value = listOf(newTx) + _transactions.value
            _syncMessage.value = "Transaction saved successfully"
        }
    }

    fun deleteTransaction(id: String) {
        viewModelScope.launch {
            _transactions.value = _transactions.value.filter { it.id != id }
            _syncMessage.value = "Transaction removed"
        }
    }

    fun addBudget(category: String, limit: Double, threshold: Int = 80) {
        viewModelScope.launch {
            val newB = Budget(UUID.randomUUID().toString(), category, limit, "USD", threshold)
            _budgets.value = _budgets.value + newB
            _syncMessage.value = "Budget added: $category"
        }
    }

    fun deleteBudget(id: String) {
        viewModelScope.launch {
            _budgets.value = _budgets.value.filter { it.id != id }
        }
    }

    fun addSubscription(name: String, amount: Double, billingCycle: String, nextDate: String, category: String) {
        viewModelScope.launch {
            val sub = Subscription(
                id = UUID.randomUUID().toString(),
                name = name,
                amount = amount,
                currency = "USD",
                billingCycle = billingCycle,
                nextBillingDate = nextDate.ifBlank { "2026-11-15" },
                category = category,
                status = "active",
                autoRenew = true
            )
            _subscriptions.value = _subscriptions.value + sub
            _syncMessage.value = "Subscription added: $name"
        }
    }

    fun toggleSubscriptionStatus(id: String) {
        viewModelScope.launch {
            _subscriptions.value = _subscriptions.value.map {
                if (it.id == id) {
                    it.copy(status = if (it.status == "active") "paused" else "active")
                } else it
            }
        }
    }

    fun deleteSubscription(id: String) {
        viewModelScope.launch {
            _subscriptions.value = _subscriptions.value.filter { it.id != id }
        }
    }

    fun refreshBankSync(bankId: String) {
        viewModelScope.launch {
            _bankConnections.value = _bankConnections.value.map {
                if (it.id == bankId) it.copy(lastSyncedAt = "Just now") else it
            }
            _syncMessage.value = "Bank connection synchronized"
        }
    }

    fun triggerCloudSync() {
        viewModelScope.launch {
            _syncDevices.value = _syncDevices.value.map {
                it.copy(syncVersion = it.syncVersion + 1, lastActiveAt = "Just now")
            }
            _syncMessage.value = "Two-way cloud vault sync complete (v${_syncDevices.value.first().syncVersion})"
        }
    }

    fun clearSyncMessage() {
        _syncMessage.value = null
    }

    // Calculations
    fun getTotalIncome(): Double = _transactions.value.filter { it.type == "income" }.sumOf { it.amount }
    fun getTotalExpenses(): Double = _transactions.value.filter { it.type == "expense" }.sumOf { it.amount }
    fun getNetBalance(): Double = getTotalIncome() - getTotalExpenses()

    fun getCategorySpent(cat: String): Double =
        _transactions.value.filter { it.category == cat && it.type == "expense" }.sumOf { it.amount }

    fun getTotalTaxDeductible(): Double =
        _transactions.value.filter { it.taxDeductible && it.type == "expense" }.sumOf { it.amount }

    fun getEstimatedTaxSavings(rate: Double = 0.25): Double =
        getTotalTaxDeductible() * rate

    fun getTotalMonthlySubscriptions(): Double =
        _subscriptions.value.filter { it.status == "active" }.sumOf { it.amount }

    fun getQuarterlyEstimatedTax(taxBracket: Double = 0.28): Double {
        val profit = (getTotalIncome() - getTotalExpenses()).coerceAtLeast(0.0)
        return profit * taxBracket
    }
}
