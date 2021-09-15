package CovidMonitorApp.CovidApp.covidAPI;

public class CovidBean {

    private String fips;
    private String country;
    private String state;
    private Object county;

    public String getState() {
        return state;
    }

    public Integer getPopulation() {
        return population;
    }

    private String level;
    private Object lat;
    private String locationId;
    @com.google.gson.annotations.SerializedName("long")
    private Object longX;
    private Integer population;
    private MetricsDTO metrics;
    private RiskLevelsDTO riskLevels;
    private Integer cdcTransmissionLevel;

    public MetricsDTO getMetrics() {
        return metrics;
    }

    public ActualsDTO getActuals() {
        return actuals;
    }

    private ActualsDTO actuals;
    public String lastUpdatedDate;
    private String url;

    public static class MetricsDTO {
        private Double testPositivityRatio;
        private TestPositivityRatioDetailsDTO testPositivityRatioDetails;
        private Double caseDensity;
        private Double contactTracerCapacityRatio;
        private Double infectionRate;
        private Double infectionRateCI90;
        private Double icuHeadroomRatio;
        private IcuHeadroomDetailsDTO icuHeadroomDetails;
        private Double icuCapacityRatio;
        private Double vaccinationsInitiatedRatio;
        private Double vaccinationsCompletedRatio;

        public Double getTestPositivityRatio() {
            return testPositivityRatio;
        }

        public void setTestPositivityRatio(Double testPositivityRatio) {
            this.testPositivityRatio = testPositivityRatio;
        }

        public TestPositivityRatioDetailsDTO getTestPositivityRatioDetails() {
            return testPositivityRatioDetails;
        }

        public void setTestPositivityRatioDetails(TestPositivityRatioDetailsDTO testPositivityRatioDetails) {
            this.testPositivityRatioDetails = testPositivityRatioDetails;
        }

        public Double getCaseDensity() {
            return caseDensity;
        }

        public void setCaseDensity(Double caseDensity) {
            this.caseDensity = caseDensity;
        }

        public Double getContactTracerCapacityRatio() {
            return contactTracerCapacityRatio;
        }

        public void setContactTracerCapacityRatio(Double contactTracerCapacityRatio) {
            this.contactTracerCapacityRatio = contactTracerCapacityRatio;
        }

        public Double getInfectionRate() {
            return infectionRate;
        }

        public void setInfectionRate(Double infectionRate) {
            this.infectionRate = infectionRate;
        }

        public Double getInfectionRateCI90() {
            return infectionRateCI90;
        }

        public void setInfectionRateCI90(Double infectionRateCI90) {
            this.infectionRateCI90 = infectionRateCI90;
        }

        public Double getIcuHeadroomRatio() {
            return icuHeadroomRatio;
        }

        public void setIcuHeadroomRatio(Double icuHeadroomRatio) {
            this.icuHeadroomRatio = icuHeadroomRatio;
        }

        public IcuHeadroomDetailsDTO getIcuHeadroomDetails() {
            return icuHeadroomDetails;
        }

        public void setIcuHeadroomDetails(IcuHeadroomDetailsDTO icuHeadroomDetails) {
            this.icuHeadroomDetails = icuHeadroomDetails;
        }

        public Double getIcuCapacityRatio() {
            return icuCapacityRatio;
        }

        public void setIcuCapacityRatio(Double icuCapacityRatio) {
            this.icuCapacityRatio = icuCapacityRatio;
        }

        public Double getVaccinationsInitiatedRatio() {
            return vaccinationsInitiatedRatio;
        }

        public void setVaccinationsInitiatedRatio(Double vaccinationsInitiatedRatio) {
            this.vaccinationsInitiatedRatio = vaccinationsInitiatedRatio;
        }

        public Double getVaccinationsCompletedRatio() {
            return vaccinationsCompletedRatio;
        }

        public void setVaccinationsCompletedRatio(Double vaccinationsCompletedRatio) {
            this.vaccinationsCompletedRatio = vaccinationsCompletedRatio;
        }

        public static class TestPositivityRatioDetailsDTO {
            private String source;

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }

        public static class IcuHeadroomDetailsDTO {
            private Integer currentIcuCovid;
            private String currentIcuCovidMethod;
            private Integer currentIcuNonCovid;
            private String currentIcuNonCovidMethod;

            public Integer getCurrentIcuCovid() {
                return currentIcuCovid;
            }

            public void setCurrentIcuCovid(Integer currentIcuCovid) {
                this.currentIcuCovid = currentIcuCovid;
            }

            public String getCurrentIcuCovidMethod() {
                return currentIcuCovidMethod;
            }

            public void setCurrentIcuCovidMethod(String currentIcuCovidMethod) {
                this.currentIcuCovidMethod = currentIcuCovidMethod;
            }

            public Integer getCurrentIcuNonCovid() {
                return currentIcuNonCovid;
            }

            public void setCurrentIcuNonCovid(Integer currentIcuNonCovid) {
                this.currentIcuNonCovid = currentIcuNonCovid;
            }

            public String getCurrentIcuNonCovidMethod() {
                return currentIcuNonCovidMethod;
            }

            public void setCurrentIcuNonCovidMethod(String currentIcuNonCovidMethod) {
                this.currentIcuNonCovidMethod = currentIcuNonCovidMethod;
            }
        }
    }

    public static class RiskLevelsDTO {
        private Integer overall;
        private Integer testPositivityRatio;
        private Integer caseDensity;
        private Integer contactTracerCapacityRatio;
        private Integer infectionRate;
        private Integer icuHeadroomRatio;
        private Integer icuCapacityRatio;

        public Integer getOverall() {
            return overall;
        }

        public void setOverall(Integer overall) {
            this.overall = overall;
        }

        public Integer getTestPositivityRatio() {
            return testPositivityRatio;
        }

        public void setTestPositivityRatio(Integer testPositivityRatio) {
            this.testPositivityRatio = testPositivityRatio;
        }

        public Integer getCaseDensity() {
            return caseDensity;
        }

        public void setCaseDensity(Integer caseDensity) {
            this.caseDensity = caseDensity;
        }

        public Integer getContactTracerCapacityRatio() {
            return contactTracerCapacityRatio;
        }

        public void setContactTracerCapacityRatio(Integer contactTracerCapacityRatio) {
            this.contactTracerCapacityRatio = contactTracerCapacityRatio;
        }

        public Integer getInfectionRate() {
            return infectionRate;
        }

        public void setInfectionRate(Integer infectionRate) {
            this.infectionRate = infectionRate;
        }

        public Integer getIcuHeadroomRatio() {
            return icuHeadroomRatio;
        }

        public void setIcuHeadroomRatio(Integer icuHeadroomRatio) {
            this.icuHeadroomRatio = icuHeadroomRatio;
        }

        public Integer getIcuCapacityRatio() {
            return icuCapacityRatio;
        }

        public void setIcuCapacityRatio(Integer icuCapacityRatio) {
            this.icuCapacityRatio = icuCapacityRatio;
        }
    }

    public static class ActualsDTO {
        private Integer cases;
        private Integer deaths;
        private Integer positiveTests;
        private Integer negativeTests;
        private Integer contactTracers;
        private HospitalBedsDTO hospitalBeds;
        private IcuBedsDTO icuBeds;
        private Integer newCases;
        private Integer newDeaths;
        private Integer vaccinesDistributed;
        private Integer vaccinationsInitiated;
        private Integer vaccinationsCompleted;
        private Integer vaccinesAdministered;
        private Object vaccinesAdministeredDemographics;
        private Object vaccinationsInitiatedDemographics;

        public Integer getCases() {
            return cases;
        }

        public void setCases(Integer cases) {
            this.cases = cases;
        }

        public Integer getDeaths() {
            return deaths;
        }

        public void setDeaths(Integer deaths) {
            this.deaths = deaths;
        }

        public Integer getPositiveTests() {
            return positiveTests;
        }

        public void setPositiveTests(Integer positiveTests) {
            this.positiveTests = positiveTests;
        }

        public Integer getNegativeTests() {
            return negativeTests;
        }

        public void setNegativeTests(Integer negativeTests) {
            this.negativeTests = negativeTests;
        }

        public Integer getContactTracers() {
            return contactTracers;
        }

        public void setContactTracers(Integer contactTracers) {
            this.contactTracers = contactTracers;
        }

        public HospitalBedsDTO getHospitalBeds() {
            return hospitalBeds;
        }

        public void setHospitalBeds(HospitalBedsDTO hospitalBeds) {
            this.hospitalBeds = hospitalBeds;
        }

        public IcuBedsDTO getIcuBeds() {
            return icuBeds;
        }

        public void setIcuBeds(IcuBedsDTO icuBeds) {
            this.icuBeds = icuBeds;
        }

        public Integer getNewCases() {
            return newCases;
        }

        public void setNewCases(Integer newCases) {
            this.newCases = newCases;
        }

        public Integer getNewDeaths() {
            return newDeaths;
        }

        public void setNewDeaths(Integer newDeaths) {
            this.newDeaths = newDeaths;
        }

        public Integer getVaccinesDistributed() {
            return vaccinesDistributed;
        }

        public void setVaccinesDistributed(Integer vaccinesDistributed) {
            this.vaccinesDistributed = vaccinesDistributed;
        }

        public Integer getVaccinationsInitiated() {
            return vaccinationsInitiated;
        }

        public void setVaccinationsInitiated(Integer vaccinationsInitiated) {
            this.vaccinationsInitiated = vaccinationsInitiated;
        }

        public Integer getVaccinationsCompleted() {
            return vaccinationsCompleted;
        }

        public void setVaccinationsCompleted(Integer vaccinationsCompleted) {
            this.vaccinationsCompleted = vaccinationsCompleted;
        }

        public Integer getVaccinesAdministered() {
            return vaccinesAdministered;
        }

        public void setVaccinesAdministered(Integer vaccinesAdministered) {
            this.vaccinesAdministered = vaccinesAdministered;
        }

        public Object getVaccinesAdministeredDemographics() {
            return vaccinesAdministeredDemographics;
        }

        public void setVaccinesAdministeredDemographics(Object vaccinesAdministeredDemographics) {
            this.vaccinesAdministeredDemographics = vaccinesAdministeredDemographics;
        }

        public Object getVaccinationsInitiatedDemographics() {
            return vaccinationsInitiatedDemographics;
        }

        public void setVaccinationsInitiatedDemographics(Object vaccinationsInitiatedDemographics) {
            this.vaccinationsInitiatedDemographics = vaccinationsInitiatedDemographics;
        }

        public static class HospitalBedsDTO {
            private Integer capacity;
            private Integer currentUsageTotal;
            private Integer currentUsageCovid;
            private Double typicalUsageRate;

            public Integer getCapacity() {
                return capacity;
            }

            public void setCapacity(Integer capacity) {
                this.capacity = capacity;
            }

            public Integer getCurrentUsageTotal() {
                return currentUsageTotal;
            }

            public void setCurrentUsageTotal(Integer currentUsageTotal) {
                this.currentUsageTotal = currentUsageTotal;
            }

            public Integer getCurrentUsageCovid() {
                return currentUsageCovid;
            }

            public void setCurrentUsageCovid(Integer currentUsageCovid) {
                this.currentUsageCovid = currentUsageCovid;
            }

            public Double getTypicalUsageRate() {
                return typicalUsageRate;
            }

            public void setTypicalUsageRate(Double typicalUsageRate) {
                this.typicalUsageRate = typicalUsageRate;
            }
        }

        public static class IcuBedsDTO {
            private Integer capacity;
            private Integer currentUsageTotal;
            private Integer currentUsageCovid;
            private Double typicalUsageRate;

            public Integer getCapacity() {
                return capacity;
            }

            public void setCapacity(Integer capacity) {
                this.capacity = capacity;
            }

            public Integer getCurrentUsageTotal() {
                return currentUsageTotal;
            }

            public void setCurrentUsageTotal(Integer currentUsageTotal) {
                this.currentUsageTotal = currentUsageTotal;
            }

            public Integer getCurrentUsageCovid() {
                return currentUsageCovid;
            }

            public void setCurrentUsageCovid(Integer currentUsageCovid) {
                this.currentUsageCovid = currentUsageCovid;
            }

            public Double getTypicalUsageRate() {
                return typicalUsageRate;
            }

            public void setTypicalUsageRate(Double typicalUsageRate) {
                this.typicalUsageRate = typicalUsageRate;
            }
        }
    }
}
