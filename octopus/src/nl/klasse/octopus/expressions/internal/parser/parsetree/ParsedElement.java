package nl.klasse.octopus.expressions.internal.parser.parsetree;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;

/** @version $Id: ParsedElement.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public abstract class ParsedElement {

  /** Creates a new instance of ParsedElement
   */
  public ParsedElement() {
  }

  /**
   * beginLine and beginColumn describe the position of the first character
   * of this token; endLine and endColumn describe the position of the
   * last character of this token.
   */
  private Token startToken = null;
  private Token endToken   = null;

  /** Set the first token of the parsed element to b.
   */
  public void startAt(Token b) {
    startToken = b;
    // set end token if empty
    if( endToken == null ) { endToken = b; }
  }

  /**
   * @return Token The first token in the input that matched this element.
   */
  public Token getStart() {
      return startToken;
  }

  /** Set the lasttoken of the parsed element to b.
   */
  public void endAt(Token b) {
    endToken = b;
  }

  /**
   * @return Token The last token in the input that matched this element.
   */
  public Token getEnd() {
      return endToken;
  }

  public int getStartLine() {
      if( getStart() == null ){
          return 0;
      } else {
          return getStart().beginLine;
      }
  }

  public int getStartColumn() {
      if( getStart() == null ){
          return 0;
      } else {
          return getStart().beginColumn;
      }
  }

  public int getEndLine() {
      if( getEnd() == null ){
          return 0;
      } else {
          return getEnd().endLine;
      }
  }

  public int getEndColumn() {
      if( getEnd() == null ){
          return 0;
      } else {
          return getEnd().endColumn;
      }
  }

  /** Re-arrange this expression such that all nested operators will be on the
   *  same level. During parsing they become all bnested.
   *  The next step (in appllyPriority() ) is to re-arrange the tree to reflect
   *   the different priorities.
   */
  public void arrangeOperators() {}
  /** Re-arrange the parse tree to reflect the predefined priorities of the
   *  binary operators. Must be redefined by each subclass.
   */
  public void applyPriority(){}


}
