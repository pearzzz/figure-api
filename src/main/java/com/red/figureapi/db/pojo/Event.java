package com.red.figureapi.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * data2
 * @author 
 */
@Data
public class Event implements Serializable {
    private Integer id;

    private Integer memberId;

    private Double loanAmnt;

    private Double fundedAmnt;

    private Double fundedAmntInv;

    private Integer term;

    private Double intRate;

    private Double installment;

    private String grade;

    private Integer empLength;

    private String homeOwnership;

    private Double annualInc;

    private String verificationStatus;

    private String loanStatus;

    private String pymntPlan;

    private String purpose;

    private Double dti;

    private Double delinq2yrs;

    private Double inqLast6mths;

    private Double mthsSinceLastDelinq;

    private Double openAcc;

    private Double pubRec;

    private Double revolBal;

    private Double revolUtil;

    private Double totalAcc;

    private String initialListStatus;

    private Double totalPymnt;

    private Double totalPymntInv;

    private Double totalRecPrncp;

    private Double totalRecInt;

    private Double totalRecLateFee;

    private Double recoveries;

    private Double collectionRecoveryFee;

    private Double lastPymntAmnt;

    private Double collections12MthsExMed;

    private String applicationType;

    private Double accNowDelinq;

    private Double totCollAmt;

    private Double totCurBal;

    private Double totalRevHiLim;

    private static final long serialVersionUID = 1L;


    public Event(int id, String grade) {
        this.id = id;
        this.grade = grade;
    }
}