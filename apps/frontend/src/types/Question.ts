export interface Question {
  readonly type: string;
  readonly category: string;
  readonly question: string;
  readonly answers: readonly string[];
}
