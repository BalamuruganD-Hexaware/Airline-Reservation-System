export interface RazorpayOptions {
  key: string;
  amount: string;
  currency: string;
  name: string;
  description: string;
  image?: string;
  order_id: string;
  handler: Function;
  prefill: Prefill;
  notes: Notes;
  theme: Theme;
}

export interface Notes {
  address: string;
}

export interface Prefill {
  name: string;
  email: string;
  contact: string;
}

export interface Theme {
  color: string;
}
