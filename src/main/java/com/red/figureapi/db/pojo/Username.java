package com.red.figureapi.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * loan_rd
 * @author 
 */
@Data
public class Username implements Serializable {
    private Integer id;

    private Integer memberId;

    private Double loanAmnt;

    private Double fundedAmnt;

    private Double fundedAmntInv;

    private String term;

    private Double intRate;

    private Double installment;

    private String grade;

    private String subGrade;

    private String empTitle;

    private String empLength;

    private String homeOwnership;

    private Double annualInc;

    private String verificationStatus;

    private String issueD;

    private String loanStatus;

    private String pymntPlan;

    private String url;

    private String desc;

    private String purpose;

    private String title;

    private String zipCode;

    private String addrState;

    private Double dti;

    private Double delinq2yrs;

    private String earliestCrLine;

    private Double inqLast6mths;

    private Double mthsSinceLastDelinq;

    private Double mthsSinceLastRecord;

    private Double openAcc;

    private Double pubRec;

    private Double revolBal;

    private Double revolUtil;

    private Double totalAcc;

    private String initialListStatus;

    private Double outPrncp;

    private Double outPrncpInv;

    private Double totalPymnt;

    private Double totalPymntInv;

    private Double totalRecPrncp;

    private Double totalRecInt;

    private Double totalRecLateFee;

    private Double recoveries;

    private Double collectionRecoveryFee;

    private String lastPymntD;

    private Double lastPymntAmnt;

    private String nextPymntD;

    private String lastCreditPullD;

    private Double collections12MthsExMed;

    private Double mthsSinceLastMajorDerog;

    private Double policyCode;

    private String applicationType;

    private Double annualIncJoint;

    private Double dtiJoint;

    private String verificationStatusJoint;

    private Double accNowDelinq;

    private Double totCollAmt;

    private Double totCurBal;

    private Double openAcc6m;

    private Double openIl6m;

    private Double openIl12m;

    private Double openIl24m;

    private Double mthsSinceRcntIl;

    private Double totalBalIl;

    private Double ilUtil;

    private Double openRv12m;

    private Double openRv24m;

    private Double maxBalBc;

    private Double allUtil;

    private Double totalRevHiLim;

    private Double inqFi;

    private Double totalCuTl;

    private Double inqLast12m;

    private static final long serialVersionUID = 1L;
}