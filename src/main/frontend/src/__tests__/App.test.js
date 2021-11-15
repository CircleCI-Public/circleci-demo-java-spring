import { render, screen } from '@testing-library/react';
import App from '../App';

test('renders App at Create Account Page', () => {
  render(<App />);
  const headerElement = screen.getByText('Create Account');
  expect(headerElement).toBeInTheDocument();
});
