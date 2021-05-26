package com.example.localtest.smartstoreapi.seller.service;

import com.example.localtest.smartstoreapi.common.Utils;
import com.example.localtest.smartstoreapi.seller.dao.SellerDAO;
import com.example.localtest.smartstoreapi.seller.mapper.SellerMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.localtest.smartstoreapi.seller.type.*;

import javax.xml.datatype.DatatypeFactory;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Service("SellerCoreService")
public class SellerCoreServiceImpl implements SellerCoreService {
    @Autowired
    SellerMapper sellerMapper;

    @Override
    public List<SellerDAO> getStoreLog() {
        return sellerMapper.getStoreLog();
    }


    // 상품주문내역 상세조회
    public long 전_상품주문내역_상세조회(String ProductOrderID, JSONObject jsonObject) throws Exception {
        //PRODUCT ID(TEST): 2001238236, 2001238220, 2001238211, 2001238206, 2001238205, 2001238197, 2001238196, 2001238195, 2001238194, 2001238174, 2001192790, 2001192789, 2001192785, 2001192784, 2001192783, 2001192210, 2001192189, 2001183501, 2001183500, 2001183499, 2001183498, 2001183496
        GetProductOrderInfoListRequest getProductOrderInfoListRequest = new GetProductOrderInfoListRequest();

        ArrayList<String> productOrderIdList = new ArrayList<String>() {{
//            add("2021052528962071");
            add(ProductOrderID);
        }};

        productOrderIdList.forEach(str -> getProductOrderInfoListRequest.getProductOrderIDList().add(str));
        Utils.setBaseSellerRequestType(getProductOrderInfoListRequest);
        getProductOrderInfoListRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "GetProductOrderInfoList"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        GetProductOrderInfoListResponse response = port.getProductOrderInfoList(getProductOrderInfoListRequest);

        ProductOrder po = null;
        SellerDAO sellerDAO = new SellerDAO();
        if (!response.getProductOrderInfoList().isEmpty()) {
            po = response.getProductOrderInfoList().get(0).getProductOrder();

            sellerDAO.setBeforeClaimStatus(Optional.ofNullable(po.getClaimStatus().value()).orElse(null));
            sellerDAO.setBeforeClaimType(Optional.ofNullable(po.getClaimType().value()).orElse(null));
            sellerDAO.setBeforeProductOrderStatus(Optional.ofNullable(po.getProductOrderStatus().value()).orElse(null));
            sellerDAO.setProductOrderId(Optional.ofNullable(po.getProductOrderID()).orElse(null));
            sellerDAO.setMallId(Optional.ofNullable(po.getMallID()).orElse(null));
            sellerDAO.setProductId(Optional.ofNullable(po.getProductID()).orElse(null));
            sellerDAO.setProductName(Optional.ofNullable(po.getProductName()).orElse(null));
            sellerDAO.setProductOption(Optional.ofNullable(po.getProductOption()).orElse(null));
        }

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품상태 : [" + response.getProductOrderInfoList().get(0).getProductOrder().getProductOrderStatus() + "]");
            log.info("TIMESTAMP : [" + response.getTimestamp() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }
        ErrorType errorType = response.getError();

        sellerDAO.setParameterJson(Optional.ofNullable(jsonObject.toJSONString()).orElse(null));

        sellerDAO.setErrorCode(errorType == null ? null : errorType.getCode());
        sellerDAO.setErrorMessage(errorType == null ? null : errorType.getMessage());
        sellerDAO.setErrorDetail(errorType == null ? null : errorType.getDetail());

        sellerMapper.afterInsertStoreLog(sellerDAO);

        return sellerDAO.getSeq();
    }

    // 상품주문내역 상세조회
    public void 후_상품주문내역_상세조회(String ProductOrderID, long targetSeq) throws Exception {
        //PRODUCT ID(TEST): 2001238236, 2001238220, 2001238211, 2001238206, 2001238205, 2001238197, 2001238196, 2001238195, 2001238194, 2001238174, 2001192790, 2001192789, 2001192785, 2001192784, 2001192783, 2001192210, 2001192189, 2001183501, 2001183500, 2001183499, 2001183498, 2001183496
        GetProductOrderInfoListRequest getProductOrderInfoListRequest = new GetProductOrderInfoListRequest();

        ArrayList<String> productOrderIdList = new ArrayList<String>() {{
//            add("2021052528962071");
            add(ProductOrderID);
        }};

        productOrderIdList.forEach(str -> getProductOrderInfoListRequest.getProductOrderIDList().add(str));
        Utils.setBaseSellerRequestType(getProductOrderInfoListRequest);
        getProductOrderInfoListRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "GetProductOrderInfoList"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        GetProductOrderInfoListResponse response = port.getProductOrderInfoList(getProductOrderInfoListRequest);

        ProductOrder po = null;
        SellerDAO sellerDAO = new SellerDAO();
        if (!response.getProductOrderInfoList().isEmpty()) {
            po = response.getProductOrderInfoList().get(0).getProductOrder();

            sellerDAO.setAfterClaimStatus(po.getClaimStatus().value());
            sellerDAO.setAfterClaimType(po.getClaimType().value());
            sellerDAO.setAfterProductOrderStatus(po.getProductOrderStatus().value());
        }

        sellerDAO.setSeq(targetSeq);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품상태 : [" + response.getProductOrderInfoList().get(0).getProductOrder().getProductOrderStatus() + "]");
            log.info("TIMESTAMP : [" + response.getTimestamp() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

        sellerMapper.updateStoreLogBySeq(sellerDAO);
    }


    // 변경상품 주문조회
    public void 변경상품_주문조회() throws Exception {
        GetChangedProductOrderListRequest getChangedProductOrderListRequest = new GetChangedProductOrderListRequest();

        // INFO: 상태별 상품주문 번호 참고: https://www.notion.so/cc36a4ac01c140a4b450e77bd12fcd80
//        getChangedProductOrderListRequest.setLastChangedStatusCode(ProductOrderChangeType.PURCHASE_DECIDED);

        GregorianCalendar fromDate = new GregorianCalendar();
        fromDate.set(2010, Calendar.MAY, 05, 20, 01, 01);

//        GregorianCalendar toDate = new GregorianCalendar();
//        toDate.set(2012, Calendar.JANUARY, 02, 01, 01, 01);

        System.out.printf("START DATE -----> [%s]%n", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fromDate.getTime()));
//        System.out.printf("END DATE -----> [%s]%n", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(toDate.getTime()));

        getChangedProductOrderListRequest.setInquiryTimeFrom(DatatypeFactory.newInstance().newXMLGregorianCalendar(fromDate));
//        getChangedProductOrderListRequest.setInquiryTimeTo(DatatypeFactory.newInstance().newXMLGregorianCalendar(toDate));


        Utils.setBaseSellerRequestType(getChangedProductOrderListRequest);
        getChangedProductOrderListRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "GetChangedProductOrderList"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        GetChangedProductOrderListResponse response = port.getChangedProductOrderList(getChangedProductOrderListRequest);

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
            log.info(String.valueOf(response.getChangedProductOrderInfoList().stream().count()));
//            response.getChangedProductOrderInfoList().stream().filter(distinctByKey(ChangedProductOrderInfo::getProductOrderID)).forEach(p -> System.out.println(p.getProductOrderID()));
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.getProductList()).isNotNull();
//        assertThat(response.getError()).isNull();
    }


    public void 발주_확인처리(String ProductOrderID) throws Exception {
        PlaceProductOrderRequest placeProductOrderRequest = new PlaceProductOrderRequest();
        placeProductOrderRequest.setProductOrderID(ProductOrderID);
        placeProductOrderRequest.setCheckReceiverAddressChanged(true);

        Utils.setBaseSellerRequestType(placeProductOrderRequest);
        placeProductOrderRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "PlaceProductOrder"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        PlaceProductOrderResponse response = port.placeProductOrder(placeProductOrderRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
            log.info("상품번호 : [" + response.isIsReceiverAddressChanged() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 취소 승인
    public void 취소_승인() throws Exception {
        ApproveCancelApplicationRequest approveCancelApplicationRequest = new ApproveCancelApplicationRequest();
        approveCancelApplicationRequest.setProductOrderID("PONO400000000002");


        Utils.setBaseSellerRequestType(approveCancelApplicationRequest);
        approveCancelApplicationRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ApproveCancelApplication"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ApproveCancelApplicationResponse response = port.approveCancelApplication(approveCancelApplicationRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 판매 취소
    public void 판매_취소() throws Exception {
        CancelSaleRequest cancelSaleRequest = new CancelSaleRequest();
        cancelSaleRequest.setProductOrderID("PONO500000000001");
        cancelSaleRequest.setCancelReasonCode(ClaimRequestReasonType.DROPPED_DELIVERY);

        Utils.setBaseSellerRequestType(cancelSaleRequest);
        cancelSaleRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "CancelSale"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        CancelSaleResponse response = port.cancelSale(cancelSaleRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 발송 지연 처리
    public void 발송지연_처리() throws Exception {
        DelayProductOrderRequest delayProductOrderRequest = new DelayProductOrderRequest();
        delayProductOrderRequest.setProductOrderID("PONO100000000004");
        delayProductOrderRequest.setDispatchDelayReasonCode(DelayedDispatchReasonType.CUSTOMER_REQUEST);

        GregorianCalendar fromDate = new GregorianCalendar();
        fromDate.set(2021, Calendar.JULY, 01);

        delayProductOrderRequest.setDispatchDueDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(fromDate));

        Utils.setBaseSellerRequestType(delayProductOrderRequest);
        delayProductOrderRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "DelayProductOrder"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        DelayProductOrderResponse response = port.delayProductOrder(delayProductOrderRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 발송 처리
    public void 발송_처리() throws Exception {
        ShipProductOrderRequest shipProductOrderRequest = new ShipProductOrderRequest();
        shipProductOrderRequest.setProductOrderID("2021052480881841");
        shipProductOrderRequest.setDeliveryMethodCode(DeliveryMethodType.RETURN_DELIVERY);

        GregorianCalendar fromDate = new GregorianCalendar();
        fromDate.set(2021, Calendar.JULY, 01);

        shipProductOrderRequest.setDispatchDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(fromDate));

        Utils.setBaseSellerRequestType(shipProductOrderRequest);
        shipProductOrderRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ShipProductOrder"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ShipProductOrderResponse response = port.shipProductOrder(shipProductOrderRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 반품 접수
    public void 반품_접수() throws Exception {
        RequestReturnRequest requestReturnRequest = new RequestReturnRequest();
        requestReturnRequest.setProductOrderID("PONO100000000004");
        requestReturnRequest.setReturnReasonCode(ClaimRequestReasonType.DROPPED_DELIVERY);
        requestReturnRequest.setCollectDeliveryMethodCode(DeliveryMethodType.RETURN_DELIVERY);

        Utils.setBaseSellerRequestType(requestReturnRequest);
        requestReturnRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "RequestReturn"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        RequestReturnResponse response = port.requestReturn(requestReturnRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 반품 승인
    public void 반품_승인() throws Exception {
        ApproveReturnApplicationRequest approveReturnApplicationRequest = new ApproveReturnApplicationRequest();
        approveReturnApplicationRequest.setProductOrderID("PONO500000000001");

        Utils.setBaseSellerRequestType(approveReturnApplicationRequest);
        approveReturnApplicationRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ApproveReturnApplication"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ApproveReturnApplicationResponse response = port.approveReturnApplication(approveReturnApplicationRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 반품 거부
    public void 반품_거부() throws Exception {
        RejectReturnRequest rejectReturnRequest = new RejectReturnRequest();
        rejectReturnRequest.setProductOrderID("PONO500000000002");

        Utils.setBaseSellerRequestType(rejectReturnRequest);
        rejectReturnRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "RejectReturn"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        RejectReturnResponse response = port.rejectReturn(rejectReturnRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 반품 보류
    public void 반품_보류() throws Exception {
        WithholdReturnRequest withholdReturnRequest = new WithholdReturnRequest();
        withholdReturnRequest.setProductOrderID("PONO100000000004");
        withholdReturnRequest.setReturnHoldCode(HoldbackClassType.ETC);
        withholdReturnRequest.setReturnHoldDetailContent("테스트사유");

        Utils.setBaseSellerRequestType(withholdReturnRequest);
        withholdReturnRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "WithholdReturn"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        WithholdReturnResponse response = port.withholdReturn(withholdReturnRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 반품 보류 해제
    public void 반품보류_해제() throws Exception {
        ReleaseReturnHoldRequest releaseReturnHoldRequest = new ReleaseReturnHoldRequest();
        releaseReturnHoldRequest.setProductOrderID("PONO100000000004");

        Utils.setBaseSellerRequestType(releaseReturnHoldRequest);
        releaseReturnHoldRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ReleaseReturnHold"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ReleaseReturnHoldResponse response = port.releaseReturnHold(releaseReturnHoldRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 교환 수거완료
    public void 교환_수거완료() throws Exception {
        ApproveCollectedExchangeRequest approveCollectedExchangeRequest = new ApproveCollectedExchangeRequest();
        approveCollectedExchangeRequest.setProductOrderID("PONO100000000004");

        Utils.setBaseSellerRequestType(approveCollectedExchangeRequest);
        approveCollectedExchangeRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ApproveCollectedExchange"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ApproveCollectedExchangeResponse response = port.approveCollectedExchange(approveCollectedExchangeRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 교환 재배송 처리
    public void 교환재배송_처리() throws Exception {
        ReDeliveryExchangeRequest reDeliveryExchangeRequest = new ReDeliveryExchangeRequest();
        reDeliveryExchangeRequest.setProductOrderID("PONO100000000004");
        reDeliveryExchangeRequest.setReDeliveryMethodCode(DeliveryMethodType.RETURN_DELIVERY);
        reDeliveryExchangeRequest.setReDeliveryCompanyCode("CJGLS");

        Utils.setBaseSellerRequestType(reDeliveryExchangeRequest);
        reDeliveryExchangeRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ReDeliveryExchange"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ReDeliveryExchangeResponse response = port.reDeliveryExchange(reDeliveryExchangeRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 교환 거부
    public void 교환_거부() throws Exception {
        RejectExchangeRequest rejectExchangeRequest = new RejectExchangeRequest();
        rejectExchangeRequest.setProductOrderID("PONO100000000004");

        Utils.setBaseSellerRequestType(rejectExchangeRequest);
        rejectExchangeRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "RejectExchange"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        RejectExchangeResponse response = port.rejectExchange(rejectExchangeRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 교환 보류
    public void 교환_보류() throws Exception {
        WithholdExchangeRequest withholdExchangeRequest = new WithholdExchangeRequest();
        withholdExchangeRequest.setProductOrderID("PONO100000000004");
        withholdExchangeRequest.setExchangeHoldCode(HoldbackClassType.ETC);
        withholdExchangeRequest.setExchangeHoldDetailContent("테스트사유");

        Utils.setBaseSellerRequestType(withholdExchangeRequest);
        withholdExchangeRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "RejectExchange"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        WithholdExchangeResponse response = port.withholdExchange(withholdExchangeRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }

    // 교환 보류 해제
    public void 교환보류_해제() throws Exception {
        ReleaseExchangeHoldRequest releaseExchangeHoldRequest = new ReleaseExchangeHoldRequest();
        releaseExchangeHoldRequest.setProductOrderID("PONO100000000004");

        Utils.setBaseSellerRequestType(releaseExchangeHoldRequest);
        releaseExchangeHoldRequest.setAccessCredentials(Utils.createAccessCredentialsFromSeller("SellerService41", "ReleaseExchangeHold"));

        SellerService sellerService = new SellerService();
        SellerServicePortType port = sellerService.getSellerServiceSOAP12Port();
        ReleaseExchangeHoldResponse response = port.releaseExchangeHold(releaseExchangeHoldRequest);

        log.info("response.toString() : " + response.toString());
        log.info("response.getWarningList() : " + response.getWarningList());

        // Response에서 상품번호 확인
        if ("SUCCESS".equals(response.getResponseType())) {
//            log.info("상품번호 : [" + response.() + "]");
        } else {
            log.info("에러 메시지 : [" + response.getError().getMessage() + "]");
            log.info("에러 코드 : [" + response.getError().getCode() + "]");
            log.info("에러 상세정보 : [" + response.getError().getDetail() + "]");
            System.out.format(String.format("1. %s(%s)", response.getError().getCode(), response.getError().getMessage()));
        }

//        assertThat(response.getResponseType()).isEqualTo("SUCCESS");
//        assertThat(response.isIsReceiverAddressChanged()).isNotNull();
//        assertThat(response.getError()).isNull();
    }
}
