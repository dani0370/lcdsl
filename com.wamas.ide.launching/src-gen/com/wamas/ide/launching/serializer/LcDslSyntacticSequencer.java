/*
 * generated by Xtext 2.10.0
 */
package com.wamas.ide.launching.serializer;

import com.google.inject.Inject;
import com.wamas.ide.launching.services.LcDslGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class LcDslSyntacticSequencer extends AbstractSyntacticSequencer {

	protected LcDslGrammarAccess grammarAccess;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (LcDslGrammarAccess) access;
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (ruleCall.getRule() == grammarAccess.getEQRule())
			return getEQToken(semanticObject, ruleCall, node);
		else if (ruleCall.getRule() == grammarAccess.getLaunchModeTypeRule())
			return getLaunchModeTypeToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal EQ: '=';
	 */
	protected String getEQToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "=";
	}
	
	/**
	 * enum LaunchModeType:
	 * 	RUN = "run" | DEBUG = "debug" | PROFILE = "profile" | COVERAGE = "coverage" | INHERIT = "inherit"
	 * ;
	 */
	protected String getLaunchModeTypeToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

}
