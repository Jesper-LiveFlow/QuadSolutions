export interface Result {
  readonly score: number;
  readonly totalQuestions: number;
  readonly userAnswers: readonly string[];
  readonly correctAnswers: readonly string[];
}
