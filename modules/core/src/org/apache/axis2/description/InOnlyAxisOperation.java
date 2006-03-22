package org.apache.axis2.description;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.i18n.Messages;
import org.apache.wsdl.WSDLConstants;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Deepal Jayasinghe
 * Date: Oct 3, 2005
 * Time: 2:06:31 PM
 */
public class InOnlyAxisOperation extends AxisOperation {
    private AxisMessage inFaultMessage;
//    private AxisMessage inMessage;
    private AxisMessage outFaultMessage;

    // this is just to store the chain , we don't use it
    private ArrayList outPhase;

    public InOnlyAxisOperation() {
        super();
        createMessage();
        setMessageExchangePattern(WSDLConstants.MEP_URI_IN_ONLY);
    }

    public InOnlyAxisOperation(QName name) {
        super(name);
        createMessage();
        setMessageExchangePattern(WSDLConstants.MEP_URI_IN_ONLY);
    }

    public void addMessage(AxisMessage message, String label) {
        if (WSDLConstants.MESSAGE_LABEL_IN_VALUE.equals(label)) {
//            inMessage = message;
            addChild("inMessage", message);
        } else {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public void addMessageContext(MessageContext msgContext, OperationContext opContext)
            throws AxisFault {
        if (!opContext.isComplete()) {
            opContext.getMessageContexts().put(MESSAGE_LABEL_IN_VALUE, msgContext);
            opContext.setComplete(true);
        } else {
            throw new AxisFault(Messages.getMessage("mepcompleted"));
        }
    }

    public void addFaultMessageContext(MessageContext msgContext, OperationContext opContext) throws AxisFault {
        HashMap mep = opContext.getMessageContexts();
        MessageContext faultMessageCtxt = (MessageContext) mep.get(MESSAGE_LABEL_FAULT_VALUE);
        if (faultMessageCtxt != null) {
            throw new AxisFault(Messages.getMessage("mepcompleted"));
        } else {
            mep.put(MESSAGE_LABEL_FAULT_VALUE, msgContext);
            opContext.setComplete(true);
            opContext.cleanup();
        }
    }

    private void createMessage() {
        AxisMessage inMessage = new AxisMessage();
        inMessage.setDirection(WSDLConstants.WSDL_MESSAGE_DIRECTION_IN);
        inMessage.setParent(this);

        inFaultMessage = new AxisMessage();
        inFaultMessage.setParent(this);

        outFaultMessage = new AxisMessage();
        outFaultMessage.setParent(this);

        outPhase = new ArrayList();

        addChild("inMessage", inMessage);
    }

    public AxisMessage getMessage(String label) {
        if (WSDLConstants.MESSAGE_LABEL_IN_VALUE.equals(label)) {
            return (AxisMessage) getChild("inMessage");
        } else {
            throw new UnsupportedOperationException(Messages.getMessage("invalidacess"));
        }
    }

    public ArrayList getPhasesInFaultFlow() {
        return inFaultMessage.getMessageFlow();
    }

    public ArrayList getPhasesOutFaultFlow() {
        return outFaultMessage.getMessageFlow();
    }

    public ArrayList getPhasesOutFlow() {
        return outPhase;
    }

    public ArrayList getRemainingPhasesInFlow() {
        return ((AxisMessage) getChild("inMessage")).getMessageFlow();
    }

    public void setPhasesInFaultFlow(ArrayList list) {
        inFaultMessage.setMessageFlow(list);
    }

    public void setPhasesOutFaultFlow(ArrayList list) {
        outFaultMessage.setMessageFlow(list);
    }

    public void setPhasesOutFlow(ArrayList list) {
        outPhase = list;
    }

    public void setRemainingPhasesInFlow(ArrayList list) {
        ((AxisMessage) getChild("inMessage")).setMessageFlow(list);
    }
}
