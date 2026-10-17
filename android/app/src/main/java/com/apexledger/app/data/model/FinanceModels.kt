package com.apexledger.app.data.model

data class Transaction(
    val id: String,
    val type: String, // "income" or "expense"
    val amount: Double,
    val currency: String = "USD",
    val category: String,
    val description: String,
    val date: String,
    val taxDeductible: Boolean = false,
    val taxCategory: String = "",
    val tags: String = "",
    val source: String = "Mobile"
)

data class Budget(
    val id: String,
    val category: String,
    val monthlyLimit: Double,
    val currency: String = "USD",
    val alertThreshold: Int = 80
)

data class Subscription(
    val id: String,
    val name: String,
    val amount: Double,
    val currency: String = "USD",
    val billingCycle: String = "monthly", // "monthly", "annual", "quarterly"
    val nextBillingDate: String,
    val category: String,
    val status: String = "active", // "active", "paused", "cancelled"
    val autoRenew: Boolean = true
)

data class BankConnection(
    val id: String,
    val institutionName: String,
    val accountMask: String,
    val accountType: String,
    val syncStatus: String = "active", // "active", "syncing", "error"
    val lastSyncedAt: String
)

data class SyncDevice(
    val id: String,
    val deviceName: String,
    val deviceType: String, // "android", "web_dashboard", "google_sites_endpoint", "ios"
    val lastActiveAt: String,
    val syncVersion: Int,
    val status: String
)

data class SpendingLeak(
    val title: String,
    val amount: Double,
    val description: String,
    val potentialAnnualSaving: Double
)

data class AiRecommendation(
    val title: String,
    val impact: String, // "High", "Medium", "Low"
    val action: String
)

data class FinancialHealth(
    val score: Int = 88,
    val summary: String = "Strong cash flow margin. 2 recurring SaaS overlaps detected for optimization. 100% of cloud dev tools are eligible for Schedule C tax deductions.",
    val monthlyBurn: Double = 3450.00,
    val monthlyIncome: Double = 7200.00,
    val taxDeductionsTotal: Double = 1420.00,
    val leaks: List<SpendingLeak> = listOf(
        SpendingLeak("Overlapping SaaS Tools", 61.0, "Duplicate design and dev AI subscriptions detected.", 732.0),
        SpendingLeak("Unplanned Dining Variance", 85.0, "Business meals ran 14% over standard budget.", 1020.0)
    ),
    val recommendations: List<AiRecommendation> = listOf(
        AiRecommendation("Consolidate Cloud & AI Subscriptions", "High", "Cancel overlapping Midjourney plan in favor of unified Gemini Workspace."),
        AiRecommendation("Log Schedule C Vehicle Mileage", "Medium", "Track 4 airport and client meeting rides to claim $0.67/mile deduction."),
        AiRecommendation("Automate 80% Budget Alert", "Low", "Enable push alerts when Food & Dining exceeds 80% monthly cap.")
    )
)

data class CurrencyConfig(
    val code: String,
    val symbol: String,
    val name: String,
    val flag: String,
    val exchangeRateToUsd: Double = 1.0
)
